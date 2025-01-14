package com.bm.container.module.market.adapter;

import android.databinding.DataBindingUtil;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.container.R;
import com.bm.container.databinding.ItemChooseCityBinding;

import java.util.ArrayList;


/**
 * @author nfmomo
 * <p>
 * //TODO 注解
 */
public class ChooseCityAdapter extends BaseAdapter { //TODO 改类名
    private Context context;
    private ArrayList<String> col = new ArrayList<>(); //TODO 改实体名
    private ItemChooseCityBinding binding; //TODO 改binding类名

    private ChooseCityCallback callback;

    public void setCallback(ChooseCityCallback callback) {
        this.callback = callback;
    }

    public interface ChooseCityCallback {
        void choose(int city);
    }

    public ChooseCityAdapter(Context context, ArrayList<String> col) { //TODO 改类名,改实体名
        this.context = context;
        this.col = col;
    }

    @Override
    public int getCount() {
        return col.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup vg) {

        if (contentView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_choose_city, vg, false);//TODO 改布局文件
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemChooseCityBinding) contentView.getTag();//TODO 改binding类名
        }

        binding.item.setText(col.get(position));
        binding.item.setOnClickListener(view -> {
            if (callback != null) {
                callback.choose(position);
            }
        });


        return contentView;
    }

}
