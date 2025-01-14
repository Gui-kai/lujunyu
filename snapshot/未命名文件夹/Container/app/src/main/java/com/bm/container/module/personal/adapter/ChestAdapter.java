package com.bm.container.module.personal.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bm.container.R;
import com.bm.container.databinding.ItemChestBinding;

import java.util.ArrayList;

/**
 * @author nfmomo
 * <p>
 * //TODO 注解
 */
public class ChestAdapter extends BaseAdapter { //TODO 改类名
    private Context context;
    private ArrayList<String> col = new ArrayList<>(); //TODO 改实体名
    private ItemChestBinding binding; //TODO 改binding类名
    private String type = "";

    public ChestAdapter(Context context, ArrayList<String> col, String type) { //TODO 改类名,改实体名
        this.context = context;
        this.col = col;
        this.type = type;
    }

    private DeleteInterface deleteInterface;

    public void setDeleteInterface(DeleteInterface deleteInterface) {
        this.deleteInterface = deleteInterface;
    }

    public interface DeleteInterface {
        void delete(int position);
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
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_chest, vg, false);//TODO 改布局文件
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemChestBinding) contentView.getTag();//TODO 改binding类名
        }

        binding.chest.setText("已添加箱号: " + col.get(position));
        binding.delete.setOnClickListener(view -> {
            deleteInterface.delete(position);
        });

        if (type.equals("0")) {
            binding.delete.setVisibility(View.GONE);
        } else if (type.equals("1")) {
            binding.delete.setVisibility(View.GONE);
        } else if (type.equals("2")) {
            binding.delete.setVisibility(View.VISIBLE);
        } else if (type.equals("3")) {
            binding.delete.setVisibility(View.GONE);
        }

        return contentView;
    }

}
