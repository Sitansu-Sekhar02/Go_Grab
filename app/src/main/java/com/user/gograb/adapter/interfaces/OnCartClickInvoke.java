package com.user.gograb.adapter.interfaces;


import com.user.gograb.services.model.TrendingMenuModel;

public interface OnCartClickInvoke {
    void OnCartItemClickInvoke(int position, int quantity, TrendingMenuModel cartModel);
}
