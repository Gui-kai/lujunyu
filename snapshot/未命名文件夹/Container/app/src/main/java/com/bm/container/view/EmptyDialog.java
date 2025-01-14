package com.bm.container.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bm.container.R;


/**
 * 空dialog
 *
 * @author nfmomo
 */
public class EmptyDialog extends Dialog {

    public EmptyDialog(Context context) {
        super(context);
    }

    public EmptyDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public EmptyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final EmptyDialog dialog = new EmptyDialog(context, R.style.AlertDialog);
            View layout = inflater.inflate(R.layout.dialog_empty, null);


            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);

            dialog.setContentView(layout);
            return dialog;
        }

    }
}
