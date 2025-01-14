package com.bm.container.module.discuss.adapter;

import android.databinding.DataBindingUtil;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bm.container.R;
import com.bm.container.Tools.Block;
import com.bm.container.databinding.ItemDiscussBinding;
import com.bm.container.module.discuss.bean.DiscussListBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @author nfmomo
 */
public class DiscussAdapter extends BaseAdapter { //TODO 改类名
    private Context context;
    private ArrayList<DiscussListBean.DataBean> col = new ArrayList<>(); //TODO 改实体名
    private ItemDiscussBinding binding; //TODO 改binding类名
    private ReplyCallback replyCallback;

    public void setReplyCallback(ReplyCallback replyCallback) {
        this.replyCallback = replyCallback;
    }

    public interface ReplyCallback {
        void reply(String id, String targetId, String targetName, String userId);
    }

    public DiscussAdapter(Context context, ArrayList<DiscussListBean.DataBean> col) { //TODO 改类名,改实体名
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
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_discuss, vg, false);//TODO 改布局文件
            contentView = binding.getRoot();
            contentView.setTag(binding);
        } else {
            binding = (ItemDiscussBinding) contentView.getTag();//TODO 改binding类名
        }

        Glide.with(context).load(Block.resolvePic(col.get(position).getHeadImageUrl())).placeholder(R.drawable.face).dontAnimate().into(binding.face);
        binding.name.setText(col.get(position).getCreateUserName());
        binding.zanNum.setText(col.get(position).getZanCount());
        binding.replyNum.setText(col.get(position).getCommentCount());
        ImageAdatper adatper = new ImageAdatper(context, col.get(position).getImageUrl());
        binding.image.setAdapter(adatper);
        String content = col.get(position).getContent();
        binding.content.setText(content);
        if (content.length() > 100) {
            binding.all.setVisibility(View.VISIBLE);
            binding.content.setText(content.substring(0, 100));
        } else {
            binding.all.setVisibility(View.GONE);
        }

        binding.all.setOnClickListener(new MyAdapterListener(content, binding.all, binding.content));
        ReplyAdatper replyAdatper = new ReplyAdatper(context, col.get(position).getCommentList());
        replyAdatper.setReplyReplyCallback((targetId, targetName) -> {
            if (replyCallback != null) {
                replyCallback.reply(col.get(position).getId(), targetId, targetName, col.get(position).getCreateUserName());
            }
        });
        binding.list.setAdapter(replyAdatper);
        binding.modelReply.setOnClickListener(v -> {
            if (replyCallback != null) {
                replyCallback.reply(col.get(position).getId(), "", "", col.get(position).getCreateUserName());
            }
        });
        return contentView;
    }

    class MyAdapterListener implements View.OnClickListener {

        private TextView all;
        private TextView content;
        private String info;

        public MyAdapterListener(String info, TextView all, TextView content) {
            this.all = all;
            this.content = content;
            this.info = info;
        }

        @Override
        public void onClick(View v) {
            if (all.getText().equals("全部")) {
                all.setText("收起");
                content.setText(info);
            } else {
                all.setText("全部");
                content.setText(info.substring(0, 100));
            }
        }
    }


}
