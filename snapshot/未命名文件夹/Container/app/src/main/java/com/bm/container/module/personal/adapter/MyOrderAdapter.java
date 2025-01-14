package com.bm.container.module.personal.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.Tools.DataUtils;
import com.bm.container.Tools.NumberUtils;
import com.bm.container.Tools.SpUtil;
import com.bm.container.databinding.ItemMyOrderBinding;
import com.bm.container.module.personal.MyOrderActivity;
import com.bm.container.module.personal.OrderDetailActivity;
import com.bm.container.module.personal.bean.OrderBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @author nfmomo
 * <p>
 * //TODO 注解
 */
public class MyOrderAdapter extends BaseAdapter { //TODO 改类名
    private Context context;
    private ArrayList<OrderBean> col = new ArrayList<>(); //TODO 改实体名
    private ItemMyOrderBinding binding; //TODO 改binding类名
    private SetClick setClick;

    public void setSetClick(SetClick setClick) {
        this.setClick = setClick;
    }

    public interface SetClick {
        void click(int type, OrderBean bean);
    }

    public MyOrderAdapter(Context context, ArrayList<OrderBean> col) { //TODO 改类名,改实体名
        this.context = context;
        this.col = col;
    }

    @Override
    public int getCount() {
        return col.size();
//		return 5;
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
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_my_order, vg, false);//TODO 改布局文件
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemMyOrderBinding) contentView.getTag();//TODO 改binding类名
        }

        OrderBean bean = col.get(position);

        binding.titleName.setText("标题:" + bean.getContainer().getTitle());

        binding.state.setText(bean.getOrderStatusTypeName());

        binding.tvHint.setVisibility(View.GONE);
        binding.tvHintOne.setVisibility(View.GONE);

        if (bean.getOrderStatusType().equals("1")) {
//			binding.state.setText("待付款");
            binding.state.setTextColor(context.getResources().getColor(R.color.daifukuan));
            binding.modelButton.setVisibility(View.VISIBLE);
            binding.leftButton.setVisibility(View.VISIBLE);
            binding.tvRemind.setVisibility(View.GONE);
            binding.leftButton.setText("取消订单");
            binding.leftButton.setOnClickListener(v -> {

                if (DataUtils.isFastClick()) {
                    Toast.makeText(context, "您的操作过于频繁，请稍后！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (setClick != null) {
                    setClick.click(0, bean);
                }
            });
            if (bean.getPaidUserInfo().getId().equals(SpUtil.getString(SpUtil.ID))) {//支付是自己，显示支付按钮
                binding.rightButton.setVisibility(View.VISIBLE);
                binding.rightButton.setText("去付款");
                binding.rightButton.setOnClickListener(v -> {

                    if (DataUtils.isFastClick()) {
                        Toast.makeText(context, "您的操作过于频繁，请稍后！", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (setClick != null) {
                        setClick.click(2, bean);
                    }
                });
            } else {
                binding.rightButton.setVisibility(View.GONE);
            }
        } else if (bean.getOrderStatusType().equals("2")) {
//			binding.state.setText("已付款");
            binding.state.setTextColor(context.getResources().getColor(R.color.yifukuan));
            binding.modelButton.setVisibility(View.VISIBLE);
            binding.tvHint.setVisibility(View.VISIBLE);
            binding.leftButton.setVisibility(View.GONE);
            binding.rightButton.setVisibility(View.GONE);
            binding.tvRemind.setVisibility(View.VISIBLE);
            binding.tvHintOne.setVisibility(View.VISIBLE);
            binding.tvRemind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (setClick != null) {
                        setClick.click(3, bean);
                    }

                }
            });

        } else if (bean.getOrderStatusType().equals("3")) {
//			binding.state.setText("待提货");
            binding.state.setTextColor(context.getResources().getColor(R.color.daitihuo));
            binding.modelButton.setVisibility(View.VISIBLE);
            binding.leftButton.setVisibility(View.GONE);
            binding.tvRemind.setVisibility(View.GONE);
            binding.tvHintOne.setVisibility(View.VISIBLE);
            binding.leftButton.setText("取消订单");
            binding.leftButton.setOnClickListener(v -> {

                if (DataUtils.isFastClick()) {
                    Toast.makeText(context, "您的操作过于频繁，请稍后！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (setClick != null) {
                    setClick.click(0, bean);
                }
            });
            binding.rightButton.setVisibility(View.VISIBLE);


            if (bean.getIsDelivery().equals("1")) {//已填写提货单
                binding.rightButton.setText("已提货");
                binding.rightButton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                binding.rightButton.setBackground(context.getResources().getDrawable(R.drawable.round_corner_blue_border));
            } else {//未填写
                binding.rightButton.setText("填写提货单");
                binding.rightButton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                binding.rightButton.setBackground(context.getResources().getDrawable(R.drawable.round_corner_blue_border));
            }

            binding.rightButton.setOnClickListener(v -> {

                if (DataUtils.isFastClick()) {
                    Toast.makeText(context, "您的操作过于频繁，请稍后！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (bean.getIsDelivery().equals("1")) {
                    if (setClick != null) {
                        setClick.click(1, bean);
                    }
                } else {
//                    Toast.makeText(context, "您还未填写提货单,请先填写提货单", Toast.LENGTH_LONG).show();
                    //去填写提货单
                    if (setClick != null) {
                        setClick.click(4, bean);
                    }
                }

            });
        } else if (bean.getOrderStatusType().equals("4")) {
//			binding.state.setText("已完成");
            binding.state.setTextColor(context.getResources().getColor(R.color.yiwancheng));
            binding.modelButton.setVisibility(View.GONE);
        } else if (bean.getOrderStatusType().equals("5")) {
//			binding.state.setText("已取消");
            binding.state.setTextColor(context.getResources().getColor(R.color.yiquxiao));
            binding.modelButton.setVisibility(View.GONE);
        }


        if (!TextUtils.isEmpty(bean.getPaidUserInfo().getAppUserType())) {
            binding.ivType.setVisibility(View.VISIBLE);
            if (bean.getPaidUserInfo().getAppUserType().equals("0")) {
                //个人
                binding.ivType.setImageResource(R.drawable.user_work_state_lv3);
            } else if (bean.getPaidUserInfo().getAppUserType().equals("1")) {
                //公司
                binding.ivType.setImageResource(R.drawable.user_work_state_lv4);
            }
        } else {
            binding.ivType.setVisibility(View.GONE);
        }

        binding.item.setOnClickListener(view -> {

            if (DataUtils.isFastClick()) {
                Toast.makeText(context, "您的操作过于频繁，请稍后！", Toast.LENGTH_LONG).show();
                return;
            }

            Intent i = new Intent(context, OrderDetailActivity.class);
            i.putExtra("content", bean);
            ((MyOrderActivity) context).startAc(i);

//            if (!bean.getOrderStatusType().equals("5")) {
//                Intent i = new Intent(context, OrderDetailActivity.class);
//                i.putExtra("content", bean);
//                ((MyOrderActivity) context).startAc(i);
//            }
        });

//        if (bean.getContainer().getContainerStatus().equals("1")){//进行中
//
//        }else if (bean.getContainer().getContainerStatus().equals("2")){//投标中
//
//        }else if (bean.getContainer().getContainerStatus().equals("3")){
//
//        }else if (bean.getContainer().getContainerStatus().equals("4")){//未付款中
//
//        }else if (bean.getContainer().getContainerStatus().equals("5")){//已完成
//
//        }


        if (!TextUtils.isEmpty(bean.getContainer().getStartPrice())) {
            binding.name.setText("投标底价:" + NumberUtils.formatDouble(Double.valueOf(bean.getContainer().getStartPrice())) + "元");
        } else if (!TextUtils.isEmpty(bean.getContainer().getPrice())) {
            binding.name.setText("报价金额:" + NumberUtils.formatDouble(Double.valueOf(bean.getContainer().getPrice())) + "元");
        } else if (!TextUtils.isEmpty(bean.getContainer().getStatusTypeName())) {
            binding.name.setText("箱况:" + bean.getContainer().getStatusTypeName());
        }


        binding.orderId.setText(bean.getOrderNo());
        Glide.with(context).load(Block.listPic(bean.getContainer().getImageUrl())).placeholder(R.drawable.face).into(binding.pic);
        binding.title.setText("箱型:" + bean.getContainer().getContainerTypeName());

        binding.count.setText("数量:" + bean.getCount() + "个");
        binding.location.setText(bean.getContainer().getCountryName() + "." + bean.getContainer().getCityName());
        binding.num.setText(bean.getCount());
        binding.allPrice.setText(bean.getPrice() + "元");

        return contentView;
    }

}
