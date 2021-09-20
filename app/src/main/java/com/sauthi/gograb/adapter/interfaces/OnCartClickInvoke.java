package com.sauthi.gograb.adapter.interfaces;


import com.sauthi.gograb.services.model.TrendingMenuModel;

public interface OnCartClickInvoke {
    void OnCartItemClickInvoke(int position, int quantity, TrendingMenuModel cartModel);
}
