package com.bm.container.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;

import com.bm.container.R;
import com.bm.container.databinding.DialogChooseCityBinding;
import com.bm.container.module.market.adapter.ChooseCityAdapter;

import java.util.ArrayList;

/**
 * 报名列表筛选选项
 *
 * @author nfmomo
 */
public class ChooseCityDialog extends Dialog {

    private Context context;

    public ChooseCityDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ChooseCityDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public static class Builder {
        private Context context;
        private DialogChooseCityBinding binding;
        private ArrayList<String> citys = new ArrayList<>();
        private OnClickListener listener;
        private OnClickListener dismissListenr;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCitys(ArrayList<String> citys) {
            this.citys = citys;
            return this;
        }

        public Builder setChooseListener(OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setDismissListener(OnClickListener dismissListenr) {
            this.dismissListenr = dismissListenr;
            return this;
        }


        public ChooseCityDialog create() {
            final ChooseCityDialog dialog = new ChooseCityDialog(context, R.style.AlertDialog);
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_choose_city, null, false);

            ChooseCityAdapter adapter = new ChooseCityAdapter(context, citys);
            adapter.setCallback(position -> {
                listener.onClick(dialog, position);
            });
            binding.cityList.setAdapter(adapter);

            binding.topModel.setOnClickListener(view -> {
                dismissListenr.onClick(dialog, 0);
            });

            binding.buttomModel.setOnClickListener(view -> {
                dismissListenr.onClick(dialog, 0);
            });

            binding.rightModel.setOnClickListener(view -> {
                dismissListenr.onClick(dialog, 0);
            });

            ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
            animation.setDuration(400);
            animation.setFillAfter(true);
            binding.cityList.setAnimation(animation);

            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
//
            dialog.setContentView(binding.getRoot());
            return dialog;

        }

    }

}
