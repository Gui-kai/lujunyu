package com.bm.container.module.market;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bm.container.R;
import com.bm.container.databinding.ActivitySaleCBinding;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.market.adapter.HistoryAdapter;

/**
 * P5_3_3 商品详情界面(出售)正在进行 (未含价格[卖家自己发布信息后选择界面])
 * <p>
 * 废弃废弃废弃废弃废弃废弃废弃废弃
 */
public class SaleCActivity extends BaseActivity {
    private ActivitySaleCBinding binding;
    private Context context;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sale_c);
        context = this;

        setLoading();
        setTopbar();
        setList();
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
        binding.topbar.title.setText(R.string.purchase_title2);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());
    }
}
