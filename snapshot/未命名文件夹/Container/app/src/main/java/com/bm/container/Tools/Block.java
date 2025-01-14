package com.bm.container.Tools;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.container.R;
import com.bm.container.http.Api;
import com.bm.container.http.Collection;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.BaseApplication;
import com.bm.container.module.BaseFragment;
import com.bm.container.module.login.bean.LoginBean;
import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.shaohui.advancedluban.Luban;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nfmomo on 17/4/7.
 */

public class Block {
    public static Context context = BaseApplication.context;
    public static int REQUEST_CODE = 200;
    public static ImageLoader loader = (ImageLoader) (context1, path, imageView) -> {
        // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
        Glide.with(context1).load(path).into(imageView);
    };
    public static ImgSelConfig config;

    /**
     * 登录 保存信息
     *
     * @param baseBean
     * @param phone
     * @param pwd
     * @param remember
     */
    public static void loginSaveInfo(LoginBean baseBean, String phone, String pwd, boolean remember) {
        SpUtil.setString(SpUtil.PHONE, phone);
        SpUtil.setString(SpUtil.PWD, pwd);
        SpUtil.setString(SpUtil.ID, baseBean.getData().getId());
        SpUtil.setString(SpUtil.USERID, baseBean.getData().getUserId());
        SpUtil.setString(SpUtil.NAME, baseBean.getData().getUserName());
        SpUtil.setString(SpUtil.USERFLAG, baseBean.getData().getUserFlag());
        SpUtil.setString(SpUtil.APPUSERTYPE, baseBean.getData().getAppUserType());
        SpUtil.setString(SpUtil.COUNTRYID, baseBean.getData().getCountry());
        SpUtil.setString(SpUtil.SAVEPHONE, phone);
        SpUtil.setString(SpUtil.SAVEPWD, pwd);

        if (remember) {
            SpUtil.setBoolean(SpUtil.REMEMNBER, true);
        } else {
            SpUtil.setBoolean(SpUtil.REMEMNBER, false);
        }
    }

    /**
     * 注销删除数据
     */
    public static void deleteInfo() {
        SpUtil.setString(SpUtil.PHONE, "");
        SpUtil.setString(SpUtil.PWD, "");
        SpUtil.setString(SpUtil.ID, "");
        SpUtil.setString(SpUtil.USERID, "");
        SpUtil.setString(SpUtil.NAME, "");
        SpUtil.setString(SpUtil.USERFLAG, "");
        SpUtil.setString(SpUtil.APPUSERTYPE, "");
        SpUtil.setString(SpUtil.COUNTRYID, "");
    }

    /**
     * 忘记密码，倒计时方法，直接加到点击时间监听中
     * <p>
     * 自动判断手机号输入框内容
     * 并更换按钮文字
     * 倒计时不结束不可再次执行
     * 页面失去焦点自动停止
     *
     * @param activity
     * @param phone
     * @param button
     */
    public static void countDownForget(BaseActivity activity, EditText phone, TextView button) {

        if (Verify.sendCode(phone, button)) {
            Collection.codeForget(activity, phone.getText().toString(), baseBean -> {
//                activity.dialog("验证码" + baseBean.getData().toString());
                if (!TextUtils.isEmpty(baseBean.getData().toString())) {
                    System.out.println("base------------------->" + baseBean.getData().toString());
                }
                activity.toast("验证码已发送至您的手机,请注意查收!");
                SpUtil.setString(SpUtil.CODEPHONE, phone.getText().toString());
                SpUtil.setString(SpUtil.CODECODE, baseBean.getData().toString());
                Observable
                        .interval(0, 1, TimeUnit.SECONDS)
                        .take(61)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(activity.bindToLifecycle())
                        .subscribe(aLong -> button.setText("(" + (60 - aLong) + ")"),
                                throwable -> button.setText(context.getString(R.string.verify_code_text)),
                                () -> button.setText(context.getString(R.string.verify_code_text)));
            });
        }

    }

    /**
     * 注册，倒计时方法，直接加到点击时间监听中
     * <p>
     * 自动判断手机号输入框内容
     * 并更换按钮文字
     * 倒计时不结束不可再次执行
     * 页面失去焦点自动停止
     *
     * @param activity
     * @param phone
     * @param button
     */
    public static void countDownRegister(BaseActivity activity, EditText phone, TextView button) {
        if (Verify.sendCode(phone, button)) {
            Collection.codeRegister(activity, phone.getText().toString(), baseBean -> {
//                activity.dialog("验证码" + baseBean.getData().toString());
                if (!TextUtils.isEmpty(baseBean.getData().toString())) {
                    System.out.println("base------------------->" + baseBean.getData().toString());
                }
                activity.toast("验证码已发送至您的手机,请注意查收!");
                SpUtil.setString(SpUtil.CODEPHONE, phone.getText().toString());
                SpUtil.setString(SpUtil.CODECODE, baseBean.getData().toString());
                Observable
                        .interval(0, 1, TimeUnit.SECONDS)
                        .take(61)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(activity.bindToLifecycle())
                        .subscribe(aLong -> button.setText("(" + (60 - aLong) + ")"),
                                throwable -> button.setText(context.getString(R.string.verify_code_text)),
                                () -> button.setText(context.getString(R.string.verify_code_text)));
            });
        }

    }

    /**
     * 注册，倒计时方法，直接加到点击时间监听中
     * <p>
     * 自动判断手机号输入框内容
     * 并更换按钮文字
     * 倒计时不结束不可再次执行
     * 页面失去焦点自动停止
     *
     * @param activity
     * @param button
     */
    public static void countDownChange(BaseActivity activity, TextView button) {
        if (Verify.sendCode(button)) {
            Collection.codeForget(activity, SpUtil.getString(SpUtil.PHONE), baseBean -> {
//                activity.dialog("验证码" + baseBean.getData().toString());
                if (!TextUtils.isEmpty(baseBean.getData().toString())) {
                    System.out.println("base------------------->" + baseBean.getData().toString());
                }
                activity.toast("验证码已发送至您的手机,请注意查收!");
                SpUtil.setString(SpUtil.CODEPHONE, SpUtil.getString(SpUtil.PHONE));
                SpUtil.setString(SpUtil.CODECODE, baseBean.getData().toString());
                Observable
                        .interval(0, 1, TimeUnit.SECONDS)
                        .take(61)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(activity.bindToLifecycle())
                        .subscribe(aLong -> button.setText("(" + (60 - aLong) + ")"),
                                throwable -> button.setText(context.getString(R.string.verify_code_text)),
                                () -> button.setText(context.getString(R.string.verify_code_text)));
            });
        }

    }

    public static void getPic(BaseActivity activity, int number) {
        config = new ImgSelConfig.Builder(activity, loader)
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(activity.getResources().getColor(R.color.transparent))
                // “确定”按钮文字颜色
                .btnTextColor(activity.getResources().getColor(R.color.white))
                // 使用沉浸式状态栏
                .statusBarColor(activity.getResources().getColor(R.color.colorPrimary))
                // 返回图标ResId
                .backResId(R.drawable.toolbar_back)
                // 标题
                .title("选图")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(activity.getResources().getColor(R.color.colorPrimary))
                // 裁剪大小。needCrop为true的时候配置
                .needCrop(false)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(number)
                .build();

        ImgSelActivity.startActivity(activity, config, REQUEST_CODE);
    }

    public static void getPic(BaseActivity activity, int number, int requestCode) {
        config = new ImgSelConfig.Builder(activity, loader)
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(activity.getResources().getColor(R.color.transparent))
                // “确定”按钮文字颜色
                .btnTextColor(activity.getResources().getColor(R.color.white))
                // 使用沉浸式状态栏
                .statusBarColor(activity.getResources().getColor(R.color.colorPrimary))
                // 返回图标ResId
                .backResId(R.drawable.toolbar_back)
                // 标题
                .title("选图")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(activity.getResources().getColor(R.color.colorPrimary))
                // 裁剪大小。needCrop为true的时候配置
                .needCrop(false)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(number)
                .build();

        ImgSelActivity.startActivity(activity, config, requestCode);
    }

    public static List<File> resolvePic(BaseActivity activity, int requestCode, int resultCode, Intent data, Consumer<List<File>> success, Consumer<Throwable> error) {
        List<String> pathList;
        List<File> beforePics = new ArrayList<>();
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            for (String path : pathList) {
                beforePics.add(new File(path));
            }
            Luban.compress(context, beforePics)
                    .putGear(Luban.THIRD_GEAR)
                    .asListObservable()
                    .subscribe(success, error);
            return beforePics;

        } else {
            activity.hideLoading();
            return beforePics;
        }
    }

    public static List<File> resolvePic(BaseActivity activity, Intent data, Consumer<List<File>> success, Consumer<Throwable> error) {
        List<String> pathList;
        List<File> beforePics = new ArrayList<>();
        if (data != null) {
            pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            for (String path : pathList) {
                beforePics.add(new File(path));
            }
            Luban.compress(context, beforePics)
                    .putGear(Luban.THIRD_GEAR)
                    .asListObservable()
                    .subscribe(success, error);
            return beforePics;

        } else {
            activity.hideLoading();
            return beforePics;
        }
    }

    /**
     * 处理图片路径
     */
    public static String resolvePic(String url) {
        if (url.isEmpty() || url.contains("http")) {
            return url;
        } else {
            Log.e("图片", Api.picUrl + url);
            return Api.picUrl + url;
        }
    }

    /**
     * 处理图片路径
     */
    public static String listPic(String url) {
        if (url.isEmpty()) {
            return url;
        } else if (url.split(",")[0].contains("http")) {
            return url.split(",")[0];
        } else {
            return Api.picUrl + url.split(",")[0];
        }

    }

    /**
     * 赞数加一
     */
    public static String zanAdd(String zan) {
        if (zan.isEmpty()) {
            zan = "0";
        }
        return "" + (Integer.parseInt(zan) + 1);

    }

    /**
     * 赞数减一
     */
    public static String zanDelete(String zan) {
        if (zan.isEmpty() || zan.equals("0")) {
            Log.e("错误", "点赞数不对，为空或为0，但其实本人点过赞");
            zan = "1";
        }
        return "" + (Integer.parseInt(zan) - 1);

    }

    /**
     * 设置文字
     */
    public static void setText(TextView tv, String content, String hint) {
        if (content.isEmpty()) {
            tv.setText(hint);
        } else {
            tv.setText(content);
        }

    }

    /**
     * 设置文字
     */
    public static void setEditText(EditText tv, String content) {
        if (content.isEmpty()) {
        } else {
            tv.setText(content);
        }
    }

    /**
     * 设置文字
     */
    public static boolean checkLogin(BaseActivity activity) {
        String id = SpUtil.getString(SpUtil.ID);
        if (id.isEmpty()) {
            activity.dialogLogin();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkLoginFragment(BaseFragment baseFragment) {
        String id = SpUtil.getString(SpUtil.ID);
        if (id.isEmpty()) {
            if (null != baseFragment) {
                baseFragment.dialogLogin();
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 动态设定空间高度
     */
    public static void autoHeight(ViewGroup view, BaseActivity activity) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        params.height = width / 2;
        view.setLayoutParams(params);
    }

    /**
     * 计算倒计时
     */
    public static String restTime(String s) {
        if (s.isEmpty()) {
            return "";
        } else {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                Date tempTime = format.parse(s);
                Long endTime = tempTime.getTime();
                Long currentTime = System.currentTimeMillis();
                if (currentTime >= endTime) {
                    return "";
                } else {
                    Long cuntTime = endTime - currentTime;
                    return formatTime(cuntTime);
                }
            } catch (ParseException e) {
                return "";
            }
        }
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;


        if (day > 0) {
            return day + "天" + hour + "小时" + minute + "分" + second + "秒";
        } else if (hour > 0) {
            return hour + "小时" + minute + "分" + second + "秒";
        } else if (minute > 0) {
            return minute + "分" + second + "秒";
        } else if (second > 0) {
            return second + "秒";
        } else {
            return "";
        }
    }

    /**
     * 是否买家身份
     */
    public static Boolean isBuyer() {
        return SpUtil.getString(SpUtil.USERFLAG).equals("0");
    }
}
