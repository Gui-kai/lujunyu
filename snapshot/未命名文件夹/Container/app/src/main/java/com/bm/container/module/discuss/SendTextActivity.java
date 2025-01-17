package com.bm.container.module.discuss;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.bm.container.R;
import com.bm.container.databinding.ActivitySendTextBinding;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;

import java.util.ArrayList;

/**
 * P6_2 纯文本贴
 */
public class SendTextActivity extends BaseActivity {
    private ActivitySendTextBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //收起键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_text);

        setTopbar();
        setLoading();
        setInput();
        setSend();
    }

    /**
     * 输入框输入
     */
    private void setInput() {
        binding.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.count.setText(s.length() + "/200字");
                s.length();
            }
        });
    }

    /**
     * 发送
     */
    private void setSend() {
        binding.sure.setOnClickListener(v -> {
            if (binding.input.getText().toString().isEmpty()) {
                toast("请输入发帖内容");
            } else {
                Collection.comment(this, binding.input.getText().toString(), new ArrayList<>(), baseBean -> {
                    dialogFinish("发帖成功！");
                });
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
        binding.topbar.title.setText(R.string.send_title);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());
    }
}
