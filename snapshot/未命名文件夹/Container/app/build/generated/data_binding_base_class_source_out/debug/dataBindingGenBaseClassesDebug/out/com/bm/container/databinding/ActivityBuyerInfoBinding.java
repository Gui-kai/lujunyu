package com.bm.container.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class ActivityBuyerInfoBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView list;

  @NonNull
  public final SwipeRefreshLayout refresh;

  @NonNull
  public final TextView tabBuy;

  @NonNull
  public final TextView tabSale;

  @NonNull
  public final TopbarBinding topbar;

  protected ActivityBuyerInfoBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView list, SwipeRefreshLayout refresh, TextView tabBuy,
      TextView tabSale, TopbarBinding topbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.list = list;
    this.refresh = refresh;
    this.tabBuy = tabBuy;
    this.tabSale = tabSale;
    this.topbar = topbar;
    setContainedBinding(this.topbar);;
  }

  @NonNull
  public static ActivityBuyerInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityBuyerInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityBuyerInfoBinding>inflate(inflater, com.bm.container.R.layout.activity_buyer_info, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityBuyerInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityBuyerInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityBuyerInfoBinding>inflate(inflater, com.bm.container.R.layout.activity_buyer_info, null, false, component);
  }

  public static ActivityBuyerInfoBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityBuyerInfoBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityBuyerInfoBinding)bind(component, view, com.bm.container.R.layout.activity_buyer_info);
  }
}
