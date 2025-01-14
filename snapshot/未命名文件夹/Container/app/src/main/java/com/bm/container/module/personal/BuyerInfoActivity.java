package com.bm.container.module.personal;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bm.container.R;
import com.bm.container.Tools.SpUtil;
import com.bm.container.databinding.ActivityBuyerInfoBinding;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.market.PurchaseAActivity;
import com.bm.container.module.market.SaleAActivity;
import com.bm.container.module.market.SaleBActivity;
import com.bm.container.module.market.adapter.MarketAdapter;
import com.bm.container.module.market.bean.InfoBean;

import java.util.ArrayList;

/**
 * P7_5 我的买箱信息
 */
public class BuyerInfoActivity extends BaseActivity {
    private ActivityBuyerInfoBinding binding;
    private Context context;
    private MarketAdapter adapter;
    private ArrayList<InfoBean> col = new ArrayList<>();
    private boolean leftTabSellected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buyer_info);
        context = this;
        setTopbar();
        setLoading();
        setList();
        setTab();

        requestInfo();

    }

    /**
     * 获取信息  发起的买箱信息 买家
     */
    private void requestInfo() {
        Collection.myBuyList(this, buyListBean -> {
            col.clear();
            col.addAll(buyListBean.getData());
            adapter.notifyDataSetChanged();
            hideLoading();
        }, throwable -> {
            col.clear();
            adapter.notifyDataSetChanged();
            hideLoading();
        });
    }

    /**
     * 获取信息  参与的买箱信息 买家
     */
//    private void requestListWithBuy() {
//        Collection.myBuyListWithBuy(this, buyListBean -> {
//            col.clear();
//            col.addAll(buyListBean.getData());
//            adapter.notifyDataSetChanged();
//            hideLoading();
//        }, throwable -> {
//            col.clear();
//            adapter.notifyDataSetChanged();
//            hideLoading();
//        });
//    }


    /**
     * w我参与的
     */
    private void requestListWithBuy() {
        Collection.listWithBid(this, bean -> {
            col.clear();
            col.addAll(bean.getData());
            adapter.notifyDataSetChanged();
            hideLoading();
        }, throwable -> {
            col.clear();
            adapter.notifyDataSetChanged();
            hideLoading();
        });
    }


    /**
     * 列表
     */
    private void setList() {
        ArrayList<String> tabs = new ArrayList<>();
        if (!leftTabSellected) {
            tabs.add(0, "0");
        } else {
            tabs.add(0, "1");
        }
//        tabs.add(0, "1");
        adapter = new MarketAdapter(context, col, tabs);
        adapter.setItemOnClick(itemOnClick);
        binding.list.setLayoutManager(new LinearLayoutManager(context));
        binding.list.setAdapter(adapter);
        binding.refresh.setOnRefreshListener(() -> {
            if (!leftTabSellected) {
                requestListWithBuy();
            } else {
                requestInfo();
            }
        });

        binding.tabBuy.setOnClickListener(view -> {
            if (!leftTabSellected) {
                tabs.add(0, "0");
                setTab();
                requestInfo();
            }
        });
        binding.tabSale.setOnClickListener(view -> {
            if (leftTabSellected) {
                tabs.add(0, "1");
                setTab();
                requestListWithBuy();
            }
        });

    }

    private void setLoading() {
        setLoading(binding.refresh);
        binding.refresh.setEnabled(true);
        binding.refresh.setColorSchemeColors(loadingColors);
    }

    private void setTopbar() {
        binding.topbar.toolbar.setTitle("");
        binding.topbar.toolbar.setNavigationIcon(R.drawable.toolbar_back);
        binding.topbar.title.setText(R.string.sbuyerinfo_title);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());
    }

    private MarketAdapter.ItemOnClick itemOnClick = new MarketAdapter.ItemOnClick() {
        @Override
        public void setOnClickListener(InfoBean bean, String vaule) {

            Intent intent;
            if (vaule.equals("0")) {
                intent = new Intent(context, PurchaseAActivity.class);
                intent.putExtra("containerId", bean.getId());
            } else {
                if (bean.getTradeType().equals("0")) {//出价
                    intent = new Intent(context, SaleAActivity.class);
                    intent.putExtra("containerId", bean.getId());
                } else {
                    intent = new Intent(context, SaleBActivity.class);
                }

            }

            intent.putExtra("content", bean);
            intent.putExtra("titleName", bean.getTitle());
            intent.putExtra("remark", bean.getRemark());
            intent.putExtra("isSuccess", bean.getIsSuccess());
            startAc(intent);
        }
    };

    private void setTab() {
        if (leftTabSellected) {
            leftTabSellected = false;
            binding.tabBuy.setTextColor(getResources().getColor(R.color.colorPrimary));
            binding.tabBuy.setBackground(getResources().getDrawable(R.drawable.tab_left_uncheck));
            binding.tabSale.setTextColor(getResources().getColor(R.color.white));
            binding.tabSale.setBackground(getResources().getDrawable(R.drawable.tab_right_checked));

        } else {
            leftTabSellected = true;
            binding.tabBuy.setTextColor(getResources().getColor(R.color.white));
            binding.tabBuy.setBackground(getResources().getDrawable(R.drawable.tab_left_checked));
            binding.tabSale.setTextColor(getResources().getColor(R.color.colorPrimary));
            binding.tabSale.setBackground(getResources().getDrawable(R.drawable.tab_right_uncheck));

        }
    }

}
