package com.bm.container.module.personal.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.Tools.NumberUtils;
import com.bm.container.databinding.ItemSalerOrderBinding;
import com.bm.container.module.BaseActivity;
import com.bm.container.module.personal.BillOfLadingActivity;
import com.bm.container.module.personal.DeliveryOrderDetailActivity;
import com.bm.container.module.personal.bean.DeliveryOrderBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @author nfmomo
 * <p>
 */
public class SalerOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DeliveryOrderBean.DataBean> col = new ArrayList<>();
    private ItemSalerOrderBinding binding;

    public SalerOrderAdapter(Context context, ArrayList<DeliveryOrderBean.DataBean> col) {
        this.context = context;
        this.col = col;
    }

    @Override
    public int getCount() {
        return col.size();
    }

    @Override
    public DeliveryOrderBean.DataBean getItem(int position) {
        return null != col ? col.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup vg) {

        if (contentView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_saler_order, vg, false);
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemSalerOrderBinding) contentView.getTag();
        }

        DeliveryOrderBean.DataBean item = getItem(position);


        Glide.with(context).load(Block.listPic(item.getContainer().getImageUrl())).placeholder(R.drawable.face).into(binding.pic);

//        binding.tvName.setText(item.getContainer().getContainerTypeName());
        binding.tvOrderCode.setText(item.getOrderNo());
//        binding.state.setText(item.getOrderStatusTypeName());

        binding.tvTitleName.setText("标题:" + item.getContainer().getTitle());

        //0未结算 1 已结算
        if (item.getFinanceInfo().getStatus().equals("0")) {
            binding.tvHintIsShow.setText("费用未结算");
        } else if (item.getFinanceInfo().getStatus().equals("1")) {
            binding.tvHintIsShow.setText("费用已结算");
        }

//        binding.tvHintIsShow.setVisibility(!TextUtils.isEmpty(item.getOrderStatusType()) && item.getOrderStatusType().equals("4") ? View.VISIBLE : View.GONE);


        if (!TextUtils.isEmpty(item.getContainer().getStartPrice())) {
            binding.name.setText("投标底价:" + NumberUtils.formatDouble(Double.valueOf(item.getContainer().getStartPrice())) + "元");
        } else if (!TextUtils.isEmpty(item.getContainer().getPrice())) {
            binding.name.setText("报价金额:" + NumberUtils.formatDouble(Double.valueOf(item.getContainer().getPrice())) + "元");
        } else if (!TextUtils.isEmpty(item.getContainer().getStatusTypeName())) {
            binding.name.setText("箱况:" + item.getContainer().getStatusTypeName());
        }

        binding.tvName.setText("箱型:" + item.getContainer().getContainerTypeName());
        binding.count.setText("数量:" + item.getCount() + "个");

        binding.piece.setText(item.getCount());
        if (!TextUtils.isEmpty(item.getPrice())) {
            binding.tvAggregatePrice.setText(NumberUtils.formatDouble(Double.valueOf(item.getPrice())) + "元");
        }
        binding.location.setText(item.getContainer().getCountryName() + "." + item.getContainer().getCityName());

        int orderStatus = Integer.valueOf(item.getIsDelivery());//0 待填写 1 已填

        int orderStatusType = Integer.valueOf(item.getOrderStatusType());
        ;

        if (orderStatus == 0) {

            binding.state.setText("待填写");
            binding.state.setTextColor(context.getResources().getColor(R.color.blue_light));
            binding.leftButton.setVisibility(View.GONE);
            binding.tip.setVisibility(View.GONE);
            binding.rightButton.setOnClickListener(view -> {
                Intent i = new Intent(context, BillOfLadingActivity.class);
                i.putExtra("type", "2");
                i.putExtra("orderNo", getItem(position).getOrderNo());
                i.putExtra("orderStatus", getItem(position).getIsDelivery());
                ((BaseActivity) context).startAc(i);
            });

        } else {

            if (orderStatusType == 4) {
                binding.state.setText("已完成");
            } else {
                binding.state.setText("已填写");
            }

            binding.state.setTextColor(context.getResources().getColor(R.color.blue_light));
            binding.leftButton.setVisibility(View.VISIBLE);
            binding.rightButton.setVisibility(View.GONE);
            binding.tip.setVisibility(View.VISIBLE);
            binding.leftButton.setOnClickListener(view -> {
                Intent i = new Intent(context, BillOfLadingActivity.class);
                i.putExtra("type", "1");
                i.putExtra("orderNo", getItem(position).getOrderNo());
                i.putExtra("orderStatus", getItem(position).getIsDelivery());
                ((BaseActivity) context).startAc(i);
            });

//            binding.state.setText("已填写");

        }


        binding.item.setOnClickListener(view -> {
            Intent intent = new Intent(context, DeliveryOrderDetailActivity.class);
            intent.putExtra("orderNo", getItem(position).getOrderNo());
            intent.putExtra("orderStatus", getItem(position).getIsDelivery());
            intent.putExtra("isSupportBill", getItem(position).getContainer().getIsSupportBill());
            if (!TextUtils.isEmpty(item.getFinanceInfo().getStatus()) && item.getFinanceInfo().getStatus().equals("1")) {
                intent.putExtra("isShow", true);
            }

            ((BaseActivity) context).startAc(intent);
        });


        return contentView;
    }

}
