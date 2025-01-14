package com.bm.container.module.personal;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.Tools.SpUtil;
import com.bm.container.constants.ConstantsTag;
import com.bm.container.databinding.FragmentPersonalBinding;
import com.bm.container.entity.UserMeesageEntity;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.BaseFragment;
import com.bm.container.module.base.MainActivity;
import com.bm.container.module.personal.bean.UserInfoBean;
import com.bm.container.module.setting.SettingActivity;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import io.reactivex.functions.Consumer;

/**
 * 碎片页 P4首页
 */

public class PersonalFragment extends BaseFragment {
    private FragmentPersonalBinding binding;
    private Context context;
    private CommonTabLayout layout;

    public static PersonalFragment getInstance(CommonTabLayout layout) {
        PersonalFragment fragment = new PersonalFragment();
        fragment.layout = layout;
        return fragment;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setInfo();
            hideSalerOrder();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()) {
            setInfo();
            hideSalerOrder();
        }

//        //获取用户信息
//        Collection.userInfo(this, new Consumer<UserInfoBean>() {
//            @Override
//            public void accept(UserInfoBean userInfoBean) throws Exception {
//
//                if (!TextUtils.isEmpty(userInfoBean.getData().getAppUserType())) {
//                    if (userInfoBean.getData().getAppUserType().equals("0")) {
//                        binding.viewOvle.setVisibility(View.GONE);
//                    } else if (userInfoBean.getData().getAppUserType().equals("1")) {
//                        binding.viewOvle.setVisibility(View.VISIBLE);
//                    }
//                }
//
//                if (!TextUtils.isEmpty(userInfoBean.getData().getIsSysRed())) {
//                    if (userInfoBean.getData().getIsSysRed().equals("0")) {
//                        binding.viewSettingMessage.setVisibility(View.GONE);
//                    } else if (userInfoBean.getData().getIsSysRed().equals("1")) {
//                        binding.viewSettingMessage.setVisibility(View.VISIBLE);
//                    }
//                }
//
//            }
//        });

        requsetMessageDot();

    }


    private void requsetMessageDot() {


        //获取用户信息
        Collection.userMessage((BaseActivity) getActivity(), new Consumer<UserMeesageEntity>() {
            @Override
            public void accept(UserMeesageEntity meesageEntity) throws Exception {

                if (null != meesageEntity && null != meesageEntity.getData()) {

                    UserMeesageEntity.DataBean itemMessage = meesageEntity.getData();
                    if (!TextUtils.isEmpty(itemMessage.getOrderBuyCount()) && Integer.valueOf(itemMessage.getOrderBuyCount()) > 0) {
                        binding.viewOvle.setVisibility(View.VISIBLE);
                    } else {
                        binding.viewOvle.setVisibility(View.GONE);
                    }

                    if (!TextUtils.isEmpty(itemMessage.getBuyCount()) && Integer.valueOf(itemMessage.getBuyCount()) > 0) {
                        binding.viewOvleTwo.setVisibility(View.VISIBLE);
                    } else {
                        binding.viewOvleTwo.setVisibility(View.GONE);
                    }

                    if (!TextUtils.isEmpty(itemMessage.getOrderSaleCount()) && Integer.valueOf(itemMessage.getOrderSaleCount()) > 0) {
                        binding.viewOvleThree.setVisibility(View.VISIBLE);
                    } else {
                        binding.viewOvleThree.setVisibility(View.GONE);
                    }

                    if (!TextUtils.isEmpty(itemMessage.getSaleCount()) && Integer.valueOf(itemMessage.getSaleCount()) > 0) {
                        binding.viewOvleFour.setVisibility(View.VISIBLE);
                    } else {
                        binding.viewOvleFour.setVisibility(View.GONE);
                    }

                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                binding.viewOvle.setVisibility(View.GONE);
            }
        });


    }


    private void requestNoteRedPoint() {

        Collection.requestContainerOrderRedPointPersonal(this, seeDeliveryOrderBean -> {
            if (null != seeDeliveryOrderBean.getData()) {
                //cancelCount  FinishCount
                binding.tvFinishCount.setText(String.valueOf(seeDeliveryOrderBean.getData().getFinishCount()));
                binding.tvCancelCount.setText(String.valueOf(seeDeliveryOrderBean.getData().getCancelCount()));
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal, container, false);
        context = getActivity();

        setLoading();
        jumpChangeInfo();
        jumpMyPosting();
        jumpMyOrder();
        jumpSalerOrder();
        jumpBuyerInfo();
        jumpSalerInfo();
        jumpMessage();
        jumpFeedback();
        jumpSetting();
        hideSalerOrder();

        View view = binding.getRoot();
        view.setClickable(true);
        return binding.getRoot();
    }

    private void hideSalerOrder() {
        if (SpUtil.getString(SpUtil.USERFLAG).equals("0")) {
            binding.salerOrderModel.setVisibility(View.GONE);
            binding.viewSalerOrderModel.setVisibility(View.GONE);
            binding.salerInfo.setVisibility(View.GONE);
            binding.viewSalerInfoModel.setVisibility(View.GONE);
        } else {
            binding.salerOrderModel.setVisibility(View.VISIBLE);
            binding.viewSalerOrderModel.setVisibility(View.VISIBLE);
            binding.salerInfo.setVisibility(View.VISIBLE);
            binding.viewSalerInfoModel.setVisibility(View.VISIBLE);
        }
    }

    private void setInfo() {
        Collection.userInfo(this, baseBean -> {
            Glide.with((MainActivity) context).load(Block.resolvePic(baseBean.getData().getImageUrl())).placeholder(R.drawable.face).dontAnimate().into(binding.face);
            if (baseBean.getData().getUserFlag().equals("0")) {
                binding.type.setText("买家");
                binding.modelBank.setVisibility(View.GONE);
                binding.modelFace.setVisibility(View.VISIBLE);
            } else {
                binding.type.setText("卖家");
                binding.modelBank.setVisibility(View.VISIBLE);
                binding.modelFace.setVisibility(View.VISIBLE);
            }
            Block.setText(binding.bankName, baseBean.getData().getAccountName(), "暂无");
            Block.setText(binding.bank, baseBean.getData().getAccountSource(), "暂无");
            Block.setText(binding.bankId, baseBean.getData().getBankAccount(), "暂无");
            Block.setText(binding.userId, baseBean.getData().getUserId(), "暂无");
            Block.setText(binding.phone, baseBean.getData().getTelephone(), "暂无");
            if (baseBean.getData().getAppUserType().equals("0")) {
                binding.nameTitle.setText("姓名");
                Block.setText(binding.name, baseBean.getData().getUserName(), "暂无");
//                Block.setText(binding.address, baseBean.getData().getAddress(), "暂无");
            } else {
                binding.nameTitle.setText("公司名称");
                Block.setText(binding.name, baseBean.getData().getCompanyName(), "暂无");
//                Block.setText(binding.address, baseBean.getData().getCompanyAddress(), "暂无");
            }
            requestNoteRedPoint();
        });
    }

    private void jumpSetting() {
        binding.setting.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, SettingActivity.class);
                startAc(i);
            }
        });
    }


    private void jumpFeedback() {
        binding.feedback.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, FeedbackActivity.class);
                startAc(i);
            }
        });
    }

    private void jumpMessage() {
        binding.message.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, MessageActivity.class);
                startAc(i);
            }
        });
    }

    /**
     * 我的卖箱信息
     */
    private void jumpSalerInfo() {
        binding.salerInfo.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, SalerInfoActivity.class);
                startAc(i);
            }
        });
    }

    /**
     * 我的买箱信息
     */
    private void jumpBuyerInfo() {
        binding.buyerInfo.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, BuyerInfoActivity.class);
                startAc(i);
            }
        });
    }

    /**
     * 我的卖箱订单
     */
    private void jumpSalerOrder() {
        binding.salerOrder.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
//                Intent i = new Intent(context, SalerOrderActivity.class);
                Intent i = new Intent(context, DeliveryOrderActivity.class);
                startAc(i);
            }
        });
    }

    private void jumpMyOrder() {
        binding.myOrder.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, MyOrderActivity.class);
                startAc(i);
            }
        });
    }

    private void jumpMyPosting() {
        binding.myPosting.setOnClickListener(view -> {
            if (Block.checkLoginFragment(PersonalFragment.this)) {
                Intent i = new Intent(context, MyPostingActivity.class);
                startAc(i);
            }
        });
    }

    private void jumpChangeInfo() {
        binding.changeInfo.setOnClickListener(view -> {
            if (Block.checkLogin((MainActivity) context)) {
                if (binding.nameTitle.getText().toString().equals("姓名")) {
                    Intent i = new Intent(context, ChangePersonalInfoActivity.class);
                    startAc(i);
                } else {
                    Intent i = new Intent(context, ChangeCompanyInfoActivity.class);
                    startAc(i);
                }
            }
        });
    }

    private void setLoading() {
        setLoading(binding.refresh);
        binding.refresh.setEnabled(false);
        binding.refresh.setColorSchemeColors(loadingColors);
    }


    @Subscriber(mode = ThreadMode.MAIN, tag = ConstantsTag.IS_LOGIN_FALSE)
    private void setTextContent(String isLogin) {

        if (!TextUtils.isEmpty(isLogin)) {
            Glide.with((MainActivity) context).load("").placeholder(R.drawable.face).dontAnimate().into(binding.face);
            binding.type.setText("");
            binding.tvFinishCount.setText("0");
            binding.tvCancelCount.setText("0");
            Block.setText(binding.bankName, "", "暂无");
            Block.setText(binding.bank, "", "暂无");
            Block.setText(binding.bankId, "", "暂无");
            Block.setText(binding.userId, "", "暂无");
            Block.setText(binding.phone, "", "暂无");
            Block.setText(binding.name, "", "暂无");
        }

    }

}
