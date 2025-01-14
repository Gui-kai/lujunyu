package com.bm.container.module.personal.adapter;

import android.databinding.DataBindingUtil;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.container.R;
import com.bm.container.databinding.ItemMessageBinding;
import com.bm.container.module.personal.bean.MessageList;

import java.util.ArrayList;

/**
 * @author nfmomo
 * <p>
 * //TODO 注解
 */
public class MessageAdapter extends BaseAdapter { //TODO 改类名
    private Context context;
    private ArrayList<MessageList.DataBean> col = new ArrayList<>(); //TODO 改实体名
    private ItemMessageBinding binding; //TODO 改binding类名

    public MessageAdapter(Context context, ArrayList<MessageList.DataBean> col) { //TODO 改类名,改实体名
        this.context = context;
        this.col = col;
    }

    @Override
    public int getCount() {
//		return col.size();
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
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_message, vg, false);//TODO 改布局文件
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemMessageBinding) contentView.getTag();//TODO 改binding类名
        }

        MessageList.DataBean data = col.get(position);
        binding.tvTitle.setText("标题：" + data.getTitle());
        binding.content.setText("内容：" + data.getContent());
        binding.time.setText(data.getSendTime());


        return contentView;
    }

}
