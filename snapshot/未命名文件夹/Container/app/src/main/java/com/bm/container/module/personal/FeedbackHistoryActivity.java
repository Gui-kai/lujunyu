package com.bm.container.module.personal;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bm.container.R;
import com.bm.container.databinding.ActivityFeedbackHistoryBinding;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.personal.adapter.FeedbackHistoryAdapter;
import com.bm.container.module.personal.bean.FeedbackListBean;

import java.util.ArrayList;


public class FeedbackHistoryActivity extends BaseActivity {
    private ActivityFeedbackHistoryBinding binding;
    private Context context;
    private FeedbackHistoryAdapter adapter;
    private int page = 1;
    private ArrayList<FeedbackListBean.DataBean> col = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback_history);
        context = this;

        setTopbar();
        setLoading();
        setList();
        getList();
    }

    /**
     * 获取列表
     */
    private void getList() {
        Collection.feedbackList(this, "" + page, bean -> {
            col.clear();
            col.addAll(bean.getData());
            adapter.notifyDataSetChanged();
        });
    }

    /**
     * 设定列表
     */
    private void setList() {
        adapter = new FeedbackHistoryAdapter(context, col);
        adapter.setSeeReply(positon -> {
            if (!col.get(positon).getFeedBackContent().isEmpty()) {
                if (col.get(positon).getStatus().equals("-100")) {
                    col.get(positon).setStatus("");
                } else {
                    col.get(positon).setStatus("-100");
                }
                adapter.notifyDataSetChanged();
            } else {
                toast("暂无回复");
            }
        });
        binding.list.setAdapter(adapter);
        binding.refresh.setOnRefreshListener(() -> {
            getList();
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
        binding.topbar.title.setText(R.string.feedback_history_title);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());
    }

}



