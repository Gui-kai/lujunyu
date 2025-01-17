package com.bm.container.module.login.adapter;

/**
 * Created by nfmomo on 17/2/27.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.container.R;

import java.util.ArrayList;
import java.util.List;

public class CityGridViewAdapter extends BaseAdapter {
    private Context context;
    // 数据集合
    private List<String> col = new ArrayList<>();
    // 是否可删除
    private Boolean[] canDelete;

    private DeleteCity deleteCity;

    public void setDeleteCity(DeleteCity deleteCity) {
        this.deleteCity = deleteCity;
    }

    public interface DeleteCity {
        void delete(int position);
    }

    public CityGridViewAdapter(Context context, List<String> col, Boolean[] canDelete) {
        this.context = context;
        this.col = col;
        this.canDelete = canDelete;
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
        ViewHolder vh = null;
        if (contentView == null) {
            vh = new ViewHolder();
            contentView = LayoutInflater.from(context).inflate(R.layout.item_gridview_city, null);
            vh.name = (TextView) contentView.findViewById(R.id.name);
            vh.delete = (ImageView) contentView.findViewById(R.id.delete);
            vh.item = (RelativeLayout) contentView.findViewById(R.id.item);
            contentView.setTag(vh);
        } else {
            vh = (ViewHolder) contentView.getTag();
        }

        vh.name.setText(col.get(position));

        vh.item.setOnClickListener(view -> {
            if (deleteCity != null) {
                deleteCity.delete(position);
            }
        });

        if (canDelete[0]) {
            vh.delete.setVisibility(View.VISIBLE);
        } else {
            vh.delete.setVisibility(View.INVISIBLE);
        }

        return contentView;
    }

    class ViewHolder {
        private TextView name;
        private ImageView delete;
        private RelativeLayout item;
    }
}