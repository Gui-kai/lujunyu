package com.bm.container.module.setting;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bm.container.R;
import com.bm.container.databinding.ActivityIntroductionBinding;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * P8_2 APP介绍及声明
 */
public class IntroductionActivity extends BaseActivity {
    private ActivityIntroductionBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_introduction);
        context = this;

        setTopbar();
        setLoading();
        initWeb();
        binding.web.loadUrl("http://www.findcontainer.cn/containerH5/appIntroduction.html");
//		Collection.introductiong(this, baseBean -> {
//			binding.web.loadUrl(baseBean.getData().toString());
//		});
    }

    @JavascriptInterface
    public void back() {
        binding.refresh.setRefreshing(true);
        Observable
                .timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    finishAc();
                    binding.refresh.setRefreshing(false);
                });
    }

    /**
     * 设定webview
     */
    private void initWeb() {
        WebSettings webSettings = binding.web.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        binding.web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                binding.web.loadUrl(url);
                return true;
            }
        });
        binding.web.addJavascriptInterface(IntroductionActivity.this, "interface");
    }

    private void setLoading() {
        setLoading(binding.refresh);
        binding.refresh.setEnabled(false);
        binding.refresh.setColorSchemeColors(loadingColors);
    }

    private void setTopbar() {
        binding.topbar.toolbar.setTitle("");
        binding.topbar.toolbar.setNavigationIcon(R.drawable.toolbar_back);
        binding.topbar.title.setText(R.string.introduction_title);
        setSupportActionBar(binding.topbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.topbar.toolbar.setNavigationOnClickListener(view -> finishAc());
    }
}
