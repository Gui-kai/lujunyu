package com.bm.container.Tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

public class LoadingProgress extends ProgressDialog {

    private static final int TIME_OUT = 10000;

    private Handler mainHandler = new Handler();

    private Runnable timeRunnable = new Runnable() {

        @Override
        public void run() {
            if (isShowing()) {
                setCancelable(true);
            }
            mainHandler.removeCallbacks(timeRunnable);
        }
    };

    public LoadingProgress(Context context) {
        super(context);

        setIndeterminate(true);
        setCancelable(false);
        setMessage("请稍候……");
    }

    public void show(boolean timeOut) {
        try {
            if (!isShowing()) {
                setCancelable(false);
                show();
                if (timeOut) {
                    mainHandler.postDelayed(timeRunnable, TIME_OUT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        mainHandler.removeCallbacks(timeRunnable);
        try {
            if (isShowing()) {
                super.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
