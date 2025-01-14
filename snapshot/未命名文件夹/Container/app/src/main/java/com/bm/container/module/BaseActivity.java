package com.bm.container.module;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.container.R;
import com.bm.container.Tools.DataUtils;
import com.bm.container.constants.ConstantsTag;
import com.bm.container.module.login.LoginActivity;
import com.bm.container.view.AlertDialog;
import com.bm.container.view.EmptyDialog;
import com.bm.container.view.PickerDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;

import java.util.List;

/**
 * Created by nfmomo on 16/12/13.
 */

public class BaseActivity extends RxAppCompatActivity {
    private int loadingColor1;
    private int loadingColor2;
    private int loadingColor3;
    protected int[] loadingColors;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EmptyDialog alert;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            EventBus.getDefault().register(this);
        } catch (NoClassDefFoundError ignored) {
        }
        initLoadingColor();
    }

    /**
     * 这里定义了loading的彩色效果
     */
    private void initLoadingColor() {
        loadingColor1 = getResources().getColor(R.color.colorPrimary);
        loadingColor2 = getResources().getColor(R.color.orange);
        loadingColor3 = getResources().getColor(R.color.grey_deep);
        loadingColors = new int[]{loadingColor1, loadingColor2, loadingColor3};
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            EventBus.getDefault().register(this);
        } catch (NoClassDefFoundError ignored) {
        }
    }

    public void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void toast(int content) {
        Toast.makeText(this, String.valueOf(content), Toast.LENGTH_SHORT).show();
    }

    /**
     * 带动画的跳转
     */
    public void startAc(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
    }

    /**
     * 带动画的跳转
     */
    public void startAcForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_open_in, R.anim.activity_open_out);
    }

    /**
     * 带动话的结束
     */
    public void finishAc() {
        finish();
        overridePendingTransition(R.anim.activity_close_in, R.anim.activity_close_out);
    }


    @Override
    public void onBackPressed() {
        finishAc();
    }


    public void setLoading(SwipeRefreshLayout layout) {
        swipeRefreshLayout = layout;
    }

    public void showLoading() {
        if (swipeRefreshLayout != null && !swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
            if (alert == null) {
                alert = new EmptyDialog.Builder(this)// builder
                        .create();
            }
            if (!alert.isShowing()) {
                alert.show();
            }
        }
    }

    public void hideLoading() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            if (alert.isShowing()) {
                alert.hide();
            }
        }
    }

    /**
     * 结束弹框
     */
    public void dialogFinish(String message) {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setCancelable(false)
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", (mdialog, which) -> {
                    mdialog.dismiss();
                    finishAc();
                })
                .create();
        alert.show();
    }

    /**
     * 结束弹框
     */
    public void dialogClareFinish(String message) {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setCancelable(false)
                .setContent(message) //TODO 内容
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", (mdialog, which) -> {
                    mdialog.dismiss();
                    finishAc();
                })
                .create();
        alert.show();
    }

    /**
     * 开启弹框
     *
     * @param message
     * @param cls
     */
    public void dialogStartAc(String message, Class<?> cls) {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", (mdialog, which) -> {
                    mdialog.dismiss();
                    finishAc();
                    Intent i = new Intent(this, cls);
                    startAc(i);
                })
                .create();
        alert.show();
    }

    /**
     * 通知弹框
     *
     * @param message
     */
    public void dialog(String message) {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setCancelable(false)
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", (mdialog, which) -> {
                    mdialog.dismiss();
                })
                .create();
        alert.show();
    }

    /**
     * 通知弹框
     *
     * @param message
     */
    public void dialog(String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setCancelable(false)
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", onClickListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();
    }


    /**
     * 版本检测弹框
     *
     * @param message
     */
    public void checkVersionDialog(String message, String url, boolean isForcedUpdating) {

        if (null == dialog) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);// builder
            alert.setTitle("版本更新");
            alert.setCancelable(false);
            alert.setContent(message);
            alert.setPositiveButton("确定", (mdialog, which) -> {
                Intent intent = new Intent("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startAc(intent);
                mdialog.dismiss();
            });
            if (!isForcedUpdating) {
                alert.setNegativeButton("取消", (mdialog, which) -> mdialog.dismiss());
            }
            dialog = alert.create();
        }

        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    /**
     * 选项弹框
     */
    public void dialogOption(List<String> col, TextView textView) {
        PickerDialog picker = new PickerDialog.Builder(this)//
                .setData(col)//TODO 数据集合
                .setCancel((mdialog, which) -> mdialog.dismiss())
                .setSure((mdialog, which) -> {
                    mdialog.dismiss();
                    textView.setText(col.get(which));
                })
                .create();
        picker.show();
    }

    /**
     * 登录弹框
     */
    public void dialogLogin() {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setContent("您还未登录，请先去登录！")
                .setCancelable(false)
                .setPositiveButton("确定", (mdialog, which) -> {
                    mdialog.dismiss();
                    EventBus.getDefault().post("isFalse", ConstantsTag.IS_LOGIN_FALSE);
                    Intent i = new Intent(this, LoginActivity.class);
                    startAc(i);
                }).create();
        alert.show();
    }

    public void dialogLogin(String message, boolean isOnclick) {
        AlertDialog alert = new AlertDialog.Builder(this)// builder
                .setCancelable(isOnclick)
                .setContent(message) //TODO 内容
                .setPositiveButton("确定", (mdialog, which) -> {
                    mdialog.dismiss();
                    Intent i = new Intent(this, LoginActivity.class);
                    startAc(i);
                })
                .create();
        alert.show();
    }

}
