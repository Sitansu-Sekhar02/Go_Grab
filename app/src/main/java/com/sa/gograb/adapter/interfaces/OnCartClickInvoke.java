package com.sa.gograb.adapter.interfaces;


import com.sa.gograb.services.model.TrendingMenuModel;

public interface OnCartClickInvoke {
    void OnCartItemClickInvoke(int position, int quantity, TrendingMenuModel cartModel);
}
