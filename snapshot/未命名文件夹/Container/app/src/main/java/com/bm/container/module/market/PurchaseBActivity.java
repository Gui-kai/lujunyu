package com.bm.container.module.market;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.bm.container.R;
import com.bm.container.databinding.ActivityPurchaseBBinding;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.market.adapter.HistoryAdapter;

/**
 * P5_2_2 商品出价界面(求购模块)正在进行 (买家自己发布信息后选择界面)
 * <p>
 * 废弃废弃废弃废弃废弃废弃废弃
 */
public class PurchaseBActivity extends BaseActivity {
    private ActivityPurchaseBBinding binding;
    private Context context;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase_b);
        context = this;

        binding.up.setVisibility(View.GONE);

        setLoading();
        setTopbar();
        setList();
        setMore();
        setBack();

    }

    /**
     * 下拉返回
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
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.up.setVisibility(View.GONE);
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

    private void setMore() {
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
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.up.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            binding.refresh.setAnimation(animation0);
            binding.up.setAnimation(animation1);
            binding.up.setVisibility(View.VISIBLE);
            animation0.startNow();
            animation1.startNow();
        });
    }

    private void closeRefreshOrLoad() {
        binding.more.onFooterLoadFinish();
        binding.more.onHeaderRefreshFinish();
    }

    private void setList() {
//		adapter = new HistoryAdapter(context, null, choosed);
        binding.historyList.setAdapter(adapter);
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
    }
}
