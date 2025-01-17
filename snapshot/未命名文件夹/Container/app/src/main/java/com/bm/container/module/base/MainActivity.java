package com.bm.container.module.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.bm.container.BuildConfig;
import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.Tools.SpUtil;
import com.bm.container.databinding.ActivityMainBinding;
import com.bm.container.entity.UserMeesageEntity;
import com.bm.container.http.BaseBean;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.base.Entity.CheckVersionEntity;
import com.bm.container.module.discuss.DiscussFragment;
import com.bm.container.module.market.MarketFragment;
import com.bm.container.module.personal.PersonalFragment;
import com.bm.container.module.personal.bean.UserInfoBean;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;


/**
 * P4首页
 * <p>
 * 包含四个碎片页
 * P4首页 MainFragment
 * P5 交易市场 MarketFragment
 * P6 讨论区
 * P7 个人中心
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private Context context;
    private ArrayList<CustomTabEntity> entitys = new ArrayList<>();
    private String[] titles = {"首页", "交易市场", "讨论区", "个人中心"};
    private Integer[] choosedIcons = {R.drawable.main_tab1_choosed, R.drawable.main_tab2_choosed, R.drawable.main_tab3_choosed, R.drawable.main_tab4_choosed};
    private Integer[] unChoosedIcons = {R.drawable.main_tab1_nochoose, R.drawable.main_tab2_nochoose, R.drawable.main_tab3_nochoose, R.drawable.main_tab4_nochoose};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private BroadcastReceiver receiver;
    private long exitTime;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;

        initView();

    }

    private void initView() {
        initTab();
    }


    /**
     * 设定点击标签
     */
    private void initTab() {
        for (int i = 0; i < 4; i++) {
            entitys.add(new TabEntity(titles[i], choosedIcons[i], unChoosedIcons[i]));
        }
        fragments.add(MainFragment.getInstance(binding.tab));
        fragments.add(MarketFragment.getInstance(binding.tab));
        fragments.add(DiscussFragment.getInstance(binding.tab));
        fragments.add(PersonalFragment.getInstance(binding.tab));

        binding.tab.setTabData(entitys, this, R.id.framlayout, fragments);
        binding.tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                requestLogin();
                if (position == 3) {
                    Block.checkLogin(MainActivity.this);
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    class TabEntity implements CustomTabEntity {
        private String title;
        private int choosedIcon;
        private int unChoosedIcon;

        public TabEntity(String title, int choosedIcon, int unChoosedIcon) {
            this.title = title;
            this.choosedIcon = choosedIcon;
            this.unChoosedIcon = unChoosedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return choosedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unChoosedIcon;
        }
    }

    /**
     * 支付成功标识复位，要求支付成功后返回首页
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (SpUtil.getBoolean(SpUtil.PAYSUCCESS, false)) {
            binding.tab.setCurrentTab(0);
            SpUtil.setBoolean(SpUtil.PAYSUCCESS, false);
        } else if (SpUtil.getBoolean(SpUtil.SUCCESSFUL_BID_SUCCESS, false)) {//出价成功后切换到交易市场fragment
            binding.tab.setCurrentTab(1);
            SpUtil.setBoolean(SpUtil.SUCCESSFUL_BID_SUCCESS, false);
        } else if (SpUtil.getBoolean(SpUtil.SELL_BOX_SUCCESSFULLY_RELEASED, false)) {
            binding.tab.setCurrentTab(1);
//            SpUtil.setBoolean(SpUtil.SELL_BOX_SUCCESSFULLY_RELEASED, false);
        }

        if (isFirst) {
            isFirst = false;
            doCheckVersion();
        }


//        //获取用户信息
//        Collection.userInfo(this, new Consumer<UserInfoBean>() {
//            @Override
//            public void accept(UserInfoBean userInfoBean) throws Exception {
//
//                if (!TextUtils.isEmpty(userInfoBean.getData().getAppUserType())) {
//                    if (userInfoBean.getData().getAppUserType().equals("0")) {
//                        binding.tab.hideMsg(3);
//                    } else if (userInfoBean.getData().getAppUserType().equals("1")) {
//                        binding.tab.showMsg(3,0);
//                    }
//                }
//
//            }
//        });


    }


    /**
     * 版本检测
     */
    private void doCheckVersion() {

        Collection.checkVersionHtml(this, "ANDROID", String.valueOf(BuildConfig.VERSION_CODE), new Consumer<CheckVersionEntity>() {
            @Override
            public void accept(CheckVersionEntity baseBean) throws Exception {
                if (null != baseBean && null != baseBean.getData() && null != baseBean.getData().get(0)) {
                    int forced = Integer.valueOf(baseBean.getData().get(0).getAndroid_is_forced());
                    checkVersionDialog("集装箱百度市场已更新，是否前往获取最新版本？", baseBean.getData().get(0).getAndroid_url(), forced == 0 ? false : true);
                }
            }
        }, throwable -> {

        });

    }

    /**
     * 注销广播
     */
    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(receiver);
        } catch (Exception ignored) {

        }
        super.onDestroy();
    }


    private void requestLogin() {

        String tempPhone = SpUtil.getString(SpUtil.PHONE);
        String tempPwd = SpUtil.getString(SpUtil.PWD);
        if (!tempPhone.isEmpty() && !tempPwd.isEmpty()) {

            Collection.login(this, tempPhone, tempPwd, baseBean -> {
            }, throwable -> {
//                    System.out.println("base---getMessage---->"+throwable.getMessage());
                if (!throwable.getMessage().isEmpty() && throwable.getMessage().contains("禁用")) {
//                    MainActivity.this.dialogLogin(throwable.getMessage(), false);
                    MainActivity.this.dialogLogin("用户已被禁用\n如有疑问,请关注微信公众号:搜箱findcontainer", false);
                }
            });

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 上次点击时间与当前点击时间间隔小于2秒 则退出程序
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出~", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
