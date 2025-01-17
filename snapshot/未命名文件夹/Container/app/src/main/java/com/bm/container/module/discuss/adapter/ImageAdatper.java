package com.bm.container.module.discuss.adapter;

/**
 * Created by nfmomo on 17/3/3.
 */

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.container.R;
import com.bm.container.Tools.SpUtil;
import com.bm.container.databinding.ItemImageBinding;
import com.bm.container.http.Api;
import com.bm.container.module.base.FangDaImgActivity;
import com.bumptech.glide.Glide;

/**
 * @author nfmomo
 * <p>
 * //TODO 注解
 */
public class ImageAdatper extends BaseAdapter { //TODO 改类名
    private Context context;
    private String[] col; //TODO 改实体名
    private ItemImageBinding binding; //TODO 改binding类名

    public ImageAdatper(Context context, String pics) { //TODO 改类名,改实体名
        this.context = context;
        col = pics.split(",");
    }

    @Override
    public int getCount() {
        return col.length;
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
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_image, vg, false);//TODO 改布局文件
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemImageBinding) contentView.getTag();//TODO 改binding类名
        }

        Glide.with(context).load(Api.picUrl + col[position]).placeholder(R.drawable.face).dontAnimate().into(binding.image);
        binding.image.setOnClickListener(v -> {
            SpUtil.setBoolean(SpUtil.DONTNEEDREFRESH, true);
            Intent intent = new Intent(context, FangDaImgActivity.class);
            intent.putExtra("pic_position", position);
            Bundle b = new Bundle();
            b.putStringArray("img", col);
            intent.putExtras(b);
            context.startActivity(intent);
        });

        return contentView;
    }

}

