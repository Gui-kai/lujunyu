package com.bm.container.module.base.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.module.market.bean.InfoBean;
import com.bumptech.glide.Glide;
import com.hongtian.easyroundimageview.EasyRoundImageView;

import java.util.ArrayList;

/**
 * Created by kec on 2017/7/24.
 */

public class MainBuyListAdapter extends BaseAdapter {

    private ArrayList<InfoBean> colBuy;

    public MainBuyListAdapter(ArrayList<InfoBean> colBuy) {
        this.colBuy = colBuy;
    }

    @Override
    public int getCount() {
        return null != colBuy ? colBuy.size() : 0;
    }

    @Override
    public InfoBean getItem(int position) {
        return colBuy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_buy, null);

            viewHolder.pic = (EasyRoundImageView) convertView.findViewById(R.id.pic);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.num = (TextView) convertView.findViewById(R.id.num);
            viewHolder.location = (TextView) convertView.findViewById(R.id.location);
            viewHolder.people = (TextView) convertView.findViewById(R.id.people);
            viewHolder.tvTitleName = (TextView) convertView.findViewById(R.id.tvTitleName);
            viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            viewHolder.item = (LinearLayout) convertView.findViewById(R.id.item);
            viewHolder.ivType = (ImageView) convertView.findViewById(R.id.iv_type);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InfoBean bean = getItem(position);

        Glide.with(parent.getContext()).load(Block.listPic(bean.getImageUrl())).placeholder(R.drawable.pic_default).dontAnimate().into(viewHolder.pic);

        if (bean.getContainerStatus().equals("1")) {//投标中
            viewHolder.status.setBackground(parent.getContext().getResources().getDrawable(R.drawable.label_dr));
        } else {
            viewHolder.status.setBackground(parent.getContext().getResources().getDrawable(R.drawable.orange));
        }

        if (!TextUtils.isEmpty(bean.getAppUserType())) {
            viewHolder.ivType.setVisibility(View.VISIBLE);
            if (bean.getAppUserType().equals("0")) {
                //个人
                viewHolder.ivType.setImageResource(R.drawable.user_work_state_lv3);
            } else if (bean.getAppUserType().equals("1")) {
                //公司
                viewHolder.ivType.setImageResource(R.drawable.user_work_state_lv4);
            }
        } else {
            viewHolder.ivType.setVisibility(View.GONE);
        }

        viewHolder.tvTitleName.setText(bean.getTitle());
        viewHolder.status.setText(bean.getContainerStatusName());
        viewHolder.type.setText(bean.getContainerTypeName());
        viewHolder.num.setText(bean.getCount());
        viewHolder.location.setText(bean.getCountryName() + "." + bean.getCityName());
        viewHolder.people.setText(bean.getScanCount());
        viewHolder.tv_status.setText(bean.getStatusTypeName());

        return convertView;
    }

    private class ViewHolder {
        private EasyRoundImageView pic;
        private TextView status;
        private TextView type;
        private TextView num;
        private TextView location;
        private TextView people;
        private LinearLayout item;
        public TextView tvTitleName;

        public ImageView ivType;
        public TextView tv_status;
    }


}
