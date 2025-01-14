package com.bm.container.module.market;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.Tools.DateUtils;
import com.bm.container.Tools.NumberUtils;
import com.bm.container.Tools.SpUtil;
import com.bm.container.Tools.Verify;
import com.bm.container.constants.ConstantsTag;
import com.bm.container.databinding.ActivityPurchaseABinding;
import com.bm.container.http.Api;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.market.adapter.HistoryAdapter;
import com.bm.container.module.market.bean.BuyContainerBean;
import com.bm.container.module.market.bean.InfoBean;
import com.bm.container.module.market.bean.OfferBean;
import com.bumptech.glide.Glide;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * P5_2_1 商品出价界面(求购模块)正在进行（卖家出价界面）
 * 以上界面定义为a版，不用看了
 * <p>
 * 根据接口和新的需求
 * 现合并为买家自己进入看到的信息和别人进入看到的信息
 */
public class PurchaseAActivity extends BaseActivity {
    private ActivityPurchaseABinding binding;
    private Context context;
    private HistoryAdapter adapter;
    private String isSpecial = "0";
    private String isInvoice = "0";
    private ArrayList<OfferBean> col = new ArrayList<>();
    private HashMap<Integer, File> files = new HashMap<>();
    private String imyself = "1";
    private ArrayList<String> choosed = new ArrayList<>();
    private String containerId;
    private BuyContainerBean.DataBean content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //收起键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase_a);
        context = this;

        binding.up.setVisibility(View.GONE);
        binding.topbar.title.setText(R.string.purchase_title);

        setLoading();
        setTopbar();
        setList();

        if (null != getIntent()) {
            containerId = getIntent().getStringExtra("containerId");
            String isSuccess = getIntent().getStringExtra("isSuccess");

            binding.llStatus.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(isSuccess)) {
                binding.llStatus.setVisibility(View.VISIBLE);
                if (isSuccess.equals("0")) {
                    binding.tvStatus.setText("进行中");
                } else if (isSuccess.equals("1")) {
                    binding.tvStatus.setText("出售成功");
                } else if (isSuccess.equals("2")) {
                    binding.tvStatus.setText("出售失败");
                }
            }
        }


//        setContent();
//        initScrollView();
//        setMore();
//        setBack();
//        setOffer();
//        setPic();

        binding.web.loadUrl(Api.baseUrl + "/containerH5/userAgreement.html");

        requesetBrowser(containerId);

    }

    private void initScrollView() {

        binding.scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> binding.refresh.setEnabled(binding.scrollView.getScrollY() == 0));

        binding.refresh.setOnRefreshListener(() -> getOfferList());

    }


    /**
     * 请求详情信息 （围观人数每次调都会+1）
     */
    private void requesetBrowser(String orderId) {

        if (TextUtils.isEmpty(orderId)) {
            return;
        }

        Collection.requesetBrowser(this, orderId, new Consumer<BuyContainerBean>() {
            @Override
            public void accept(BuyContainerBean buyContainerBean) throws Exception {

                if (null != buyContainerBean && null != buyContainerBean.getData()) {
                    content = buyContainerBean.getData();
                    setContent();
                    initScrollView();
                    setMore();
                    setBack();
                    setOffer();
                    setPic();
                }

            }
        });


    }

    /**
     * 设定图片，箱子的
     */
    private void setPic() {
        binding.file0.setOnClickListener(v -> {
            Block.getPic(this, 1, 200);
        });
        binding.file1.setOnClickListener(v -> {
            Block.getPic(this, 1, 201);
        });
        binding.file2.setOnClickListener(v -> {
            Block.getPic(this, 1, 202);
        });
        binding.file3.setOnClickListener(v -> {
            Block.getPic(this, 1, 203);
        });
        binding.file4.setOnClickListener(v -> {
            Block.getPic(this, 1, 204);
        });
        binding.file5.setOnClickListener(v -> {
            Block.getPic(this, 1, 205);
        });
    }

    /**
     * 出价
     */
    private void setOffer() {
        binding.sure.setOnClickListener(v -> {
            if (imyself.equals("1")) {
                if (choosed.get(0).equals("-1")) {
                    dialog("请先选择卖家出价");
                } else {

                    if (content.getContainerStatus().equals("4") || content.getContainerStatus().equals("5")) {
                        //未付款中
                        toast("等待买家付款中，请勿重新选择。如超时未付款，您将可重新选择价格。");
                        return;
                    }

                    Intent i = new Intent(this, PurchaseEActivity.class);
                    i.putExtra("content", content);
                    int position = Integer.parseInt(choosed.get(0));
                    i.putExtra("choosed", col.get(position));
                    startAc(i);
                }
            } else {

                String number = binding.inputNumber.getText().toString();
                String price = binding.price.getText().toString();
                String specialPrice = binding.inputMoney.getText().toString();

                if (Verify.offerPrice(number, price, isSpecial, specialPrice)) {

                    if (TextUtils.isEmpty(specialPrice)) {
                        specialPrice = "0";
                    }

                    String finalSpecialPrice = specialPrice;
                    dialog("注：竞标方仅可对出售商品进行1次含税出价，如有特殊费用，请累计。请诚信出价,并对自己的出价负责", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                            Collection.offerPrice(PurchaseAActivity.this, content.getId(), number, price, isSpecial, finalSpecialPrice, isInvoice, files, baseBean -> {
                                getOfferList();
//						dialogFinish("成功出价！");
                                dialogFinish(baseBean.getMsg());
                                SpUtil.setBoolean(SpUtil.SUCCESSFUL_BID_SUCCESS, true);
                            });

                        }
                    });


                }
            }
        });
    }

    /**
     * 信息内容
     */
    private void setContent() {

        if (null == content) {
            return;
        }

        if (content.getCreateUser().equals(SpUtil.getString(SpUtil.ID))) {//自己
            imyself = "1";
        } else {//他人
            imyself = "0";
        }

        if (imyself.equals("1")) {
            binding.salerModel.setVisibility(View.GONE);
            binding.sure.setText("确认订单");
        } else {
            binding.salerModel.setVisibility(View.VISIBLE);
            binding.sure.setText("我要出价");
        }

        if (!TextUtils.isEmpty(content.getAppUserType())) {
            binding.ivType.setVisibility(View.VISIBLE);
            if (content.getAppUserType().equals("0")) {
                //个人
                binding.ivType.setImageResource(R.drawable.user_work_state_lv3);
            } else if (content.getAppUserType().equals("1")) {
                //公司
                binding.ivType.setImageResource(R.drawable.user_work_state_lv4);
            }
        } else {
            binding.ivType.setVisibility(View.GONE);
        }

        binding.tvTitleName.setText(content.getTitle());
        binding.location.setText(content.getCountryName() + "." + content.getCityName());
        binding.people.setText(content.getScanCount());
        binding.type.setText(content.getContainerTypeName());
        binding.status.setText(content.getStatusTypeName());
        binding.num.setText(content.getCount() + "个");
        binding.inputNumber.setText(content.getCount());

        if (!TextUtils.isEmpty(content.getStartPrice())) {
            binding.tvStartPrice.setText(NumberUtils.formatDouble(Double.valueOf(content.getStartPrice())) + "元");
            binding.llStartPrice.setVisibility(View.VISIBLE);
        } else {
            binding.llStartPrice.setVisibility(View.GONE);
        }
        binding.special.setText(content.getSpecialRemark());


//        if (content.getTradeType().equals("0")) {//销售中
//            binding.llIsGoing.setBackground(context.getResources().getDrawable(R.drawable.red_two));
//            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.red));
//        } else if (content.getTradeType().equals("1")) {//投标中
//            binding.llIsGoing.setBackground(context.getResources().getDrawable(R.drawable.orange_light));
//            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.orange));
//        }

        if (content.getContainerStatus().equals("1")) {//进行中  投标中(买箱)
            binding.llIsGoing.setBackground(context.getResources().getDrawable(R.drawable.label_dr_two));
            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.label_dr));
        } else if (content.getContainerStatus().equals("2")) {//投标中（卖箱）
            binding.llIsGoing.setBackground(context.getResources().getDrawable(R.drawable.orange_light));
            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.orange));
        } else if (content.getContainerStatus().equals("3")) {//销售中
            binding.llIsGoing.setBackground(context.getResources().getDrawable(R.drawable.red_two));
            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.red));
        } else if (content.getContainerStatus().equals("4")) {//未付款中
            binding.llIsGoing.setBackground(context.getResources().getDrawable(R.drawable.red_two));
            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.red));
        } else if (content.getContainerStatus().equals("5")) {//已完成
            binding.llIsGoing.setBackground(null);
            binding.restTime.setVisibility(View.GONE);
            binding.isGoing.setBackground(context.getResources().getDrawable(R.drawable.red));
        } else if (content.getContainerStatus().equals("6")) {//已结束
            binding.llIsGoing.setVisibility(View.GONE);
            binding.restTime.setVisibility(View.GONE);
        }


        if (!TextUtils.isEmpty(content.getAge())) {
            binding.llAge.setVisibility(View.VISIBLE);
            binding.tvAge.setText(content.getAge() + "年");
        } else {
            binding.llAge.setVisibility(View.GONE);
        }

        binding.isGoing.setText(content.getContainerStatusName());
        binding.checkGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == binding.noMoney.getId()) {
                binding.inputMoney.setEnabled(false);
                binding.inputMoney.setText("");
                isSpecial = "0";
            } else {
                binding.inputMoney.setEnabled(true);
                binding.inputMoney.setText("");
                isSpecial = "1";
            }
        });
        binding.require.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == binding.noInvoice.getId()) {
                isInvoice = "0";
            } else {
                isInvoice = "1";
            }
        });
        getOfferList();

        if (!content.getContainerStatus().equals("5") && !content.getContainerStatus().equals("6")) {


            Observable
                    .interval(0, 1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(bindToLifecycle())
                    .subscribe(aLong -> {
//                    String countTime = Block.restTime(content.getDeadLineTime());
//                    binding.restTime.setText(countTime);

                        String countTime = "";
                        //未付款中
                        if (content.getContainerStatus().equals("4")) {
                            long time = DateUtils.getLongTime(content.getCreateTime()) + 36 * 60 * 60 * 1000;//
                            String timestamp = DateUtils.getFormatTimeFromTimestamp(time, DateUtils.FORMAT_DATE_TIME_ALL);
                            countTime = Block.restTime(timestamp);
                        } else {
                            countTime = Block.restTime(content.getDeadLineTime());
                        }
                        binding.restTime.setText(countTime);


                    }, throwable -> {

                    }, () -> {

                    }, disposable -> {
//                        String countTime = Block.restTime(content.getDeadLineTime());

                        String countTime;

                        if (content.getContainerStatus().equals("4")) {
                            long time = DateUtils.getLongTime(content.getCreateTime()) + 36 * 60 * 60 * 1000;//
                            String timestamp = DateUtils.getFormatTimeFromTimestamp(time, DateUtils.FORMAT_DATE_TIME_ALL);
                            countTime = Block.restTime(timestamp);
                        } else {
                            countTime = Block.restTime(content.getDeadLineTime());
                        }

                        if (countTime.isEmpty()) {
                            dialogFinish("交易记录已过期,请在列表中重新选择");
                            disposable.dispose();
                        } else {

                        }
                    });

        }


    }

    /**
     * 供应列表
     */
    private void getOfferList() {
        if (imyself.equals("1")) {
            Collection.getAllOfferList(this, content.getId(), offerBean -> {
                col.clear();
                col.addAll(offerBean.getData());
                adapter.notifyDataSetChanged();
            });
        } else {
            Collection.getOfferList(this, content.getId(), offerBean -> {
                col.clear();
                col.addAll(offerBean.getData());
                adapter.notifyDataSetChanged();
            });
        }

    }

    /**
     * 上下拉切换页面的效果
     */
    private void setBack() {
        binding.up.setOnHeaderRefreshListener(view -> {
            closeLoading();
            TranslateAnimation animation0 = new TranslateAnimation(0, 0, 0, binding.framlayout.getHeight());
            animation0.setDuration(500);
            animation0.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    binding.up.setVisibility(View.VISIBLE);
                    binding.topbar.title.setText(R.string.app_purchase_information);

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.up.setVisibility(View.GONE);
                    binding.topbar.title.setText(R.string.purchase_title);
//
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            TranslateAnimation animation1 = new TranslateAnimation(0, 0, -binding.framlayout.getHeight(), 0);
            animation1.setDuration(500);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    binding.refresh.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.refresh.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            binding.up.setAnimation(animation0);
            binding.refresh.setAnimation(animation1);
            animation0.startNow();
            animation1.startNow();
        });
    }

    private void closeLoading() {
        binding.up.onFooterLoadFinish();
        binding.up.onHeaderRefreshFinish();
    }

    /**
     * 更多的页面信息
     */
    private void setMore() {

        binding.more.setOnHeaderRefreshListener(view -> {
            getOfferList();
        });
        binding.more.setOnFooterLoadListener(view -> {
            closeRefreshOrLoad();
            TranslateAnimation animation0 = new TranslateAnimation(0, 0, 0, -binding.framlayout.getHeight());
            animation0.setDuration(500);
            animation0.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    binding.refresh.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.refresh.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            TranslateAnimation animation1 = new TranslateAnimation(0, 0, binding.framlayout.getHeight(), 0);
            animation1.setDuration(500);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    binding.up.setVisibility(View.VISIBLE);
                    binding.topbar.title.setText(R.string.app_purchase_information);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.up.setVisibility(View.VISIBLE);
                    binding.topbar.title.setText(R.string.app_purchase_information);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            binding.refresh.setAnimation(animation0);
            binding.up.setAnimation(animation1);
            binding.up.setVisibility(View.VISIBLE);
            binding.topbar.title.setText(R.string.app_purchase_information);
            animation0.startNow();
            animation1.startNow();
        });
    }

    /**
     * 体质刷新
     */
    private void closeRefreshOrLoad() {
        binding.more.onFooterLoadFinish();
        binding.more.onHeaderRefreshFinish();
    }

    private void setList() {
        choosed.add("-1");
        binding.historyList.setFocusable(false);
        adapter = new HistoryAdapter(context, col, choosed);
        binding.historyList.setAdapter(adapter);
        binding.historyList.setOnItemClickListener((parent, view, position, id) -> {
            if (imyself.equals("1")) {
                choosed.clear();
                choosed.add("" + position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setLoading() {
        setLoading(binding.refresh);
        binding.refresh.setEnabled(false);
        binding.refresh.setColorSchemeColors(loadingColors);
    }

    private void setTopbar() {
        binding.topbar.toolbar.setTitle("");
        binding.topbar.toolbar.setNavigationIcon(R.drawable.toolbar_back);
        binding.topbar.title.setText(R.string.purchase_title);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());

        binding.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//				System.out.println("base-----onTextChanged--->"+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
//				System.out.println("base-----afterTextChanged--->"+s);
            }
        });
    }

    /**
     * 返回图片的压缩
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            showLoading();
            List<File> pics = Block.resolvePic(this, data, files1 -> {
                Log.e("压缩", "成功了！");
                Glide.with(context).load(files1.get(0)).placeholder(R.drawable.type0).dontAnimate().into(binding.file0);
                PurchaseAActivity.this.files.put(0, files1.get(0));
                PurchaseAActivity.this.hideLoading();
            }, throwable -> {
                Log.e("压缩失败", "压缩失败");
                PurchaseAActivity.this.hideLoading();
            });
        } else if (requestCode == 201) {
            showLoading();
            List<File> pics = Block.resolvePic(this, data, files -> {
                Log.e("压缩", "成功了！");
                Glide.with(context).load(files.get(0)).placeholder(R.drawable.type1).dontAnimate().into(binding.file1);
                this.files.put(1, files.get(0));
                hideLoading();
            }, throwable -> {
                Log.e("压缩失败", "压缩失败");
                hideLoading();
            });
        } else if (requestCode == 202) {
            showLoading();
            List<File> pics = Block.resolvePic(this, data, files -> {
                Log.e("压缩", "成功了！");
                Glide.with(context).load(files.get(0)).placeholder(R.drawable.type2).dontAnimate().into(binding.file2);
                this.files.put(2, files.get(0));
                hideLoading();
            }, throwable -> {
                Log.e("压缩失败", "压缩失败");
                hideLoading();
            });
        } else if (requestCode == 203) {
            showLoading();
            List<File> pics = Block.resolvePic(this, data, files -> {
                Log.e("压缩", "成功了！");
                Glide.with(context).load(files.get(0)).placeholder(R.drawable.type3).dontAnimate().into(binding.file3);
                this.files.put(3, files.get(0));
                hideLoading();
            }, throwable -> {
                Log.e("压缩失败", "压缩失败");
                hideLoading();
            });
        } else if (requestCode == 204) {
            showLoading();
            List<File> pics = Block.resolvePic(this, data, files -> {
                Log.e("压缩", "成功了！");
                Glide.with(context).load(files.get(0)).placeholder(R.drawable.type4).dontAnimate().into(binding.file4);
                this.files.put(4, files.get(0));
                hideLoading();
            }, throwable -> {
                Log.e("压缩失败", "压缩失败");
                hideLoading();
            });
        } else if (requestCode == 205) {
            showLoading();
            List<File> pics = Block.resolvePic(this, data, files -> {
                Log.e("压缩", "成功了！");
                Glide.with(context).load(files.get(0)).placeholder(R.drawable.type5).dontAnimate().into(binding.file5);
                this.files.put(5, files.get(0));
                hideLoading();
            }, throwable -> {
                Log.e("压缩失败", "压缩失败");
                hideLoading();
            });
        } else {

        }
    }


    @Subscriber(mode = ThreadMode.MAIN, tag = ConstantsTag.PAY_ACTIVIY_FINISH)
    private void finishActivity(String string) {

        if (!TextUtils.isEmpty(string)) {
            finishAc();
        }

    }

}
