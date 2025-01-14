package com.bm.container.module.personal;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.bm.container.R;
import com.bm.container.databinding.ActivityFeedbackBinding;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;

/**
 * P7_8 意见反馈
 */
public class FeedbackActivity extends BaseActivity {
    private ActivityFeedbackBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback);
        context = this;
        setLoading();
        setTopbar();
        submitContent();
    }

    /**
     * 提交信息
     */
    private void submitContent() {
        binding.sure.setOnClickListener(v -> {
            String content = binding.content.getText().toString();
            if (!content.isEmpty()) {
                Collection.saveFeedback(this, content, bean -> {
                    dialogFinish("提交成功，感谢您的反馈！");
                });
            } else {
                toast("请输入反馈信息！");
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
        binding.topbar.title.setText(R.string.feedback_title);
        binding.topbar.rightTextModel.setVisibility(View.VISIBLE);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());
        binding.topbar.rightTextModel.setOnClickListener(view -> {
            Intent i = new Intent(context, FeedbackHistoryActivity.class);
            startAc(i);
        });
    }
}
