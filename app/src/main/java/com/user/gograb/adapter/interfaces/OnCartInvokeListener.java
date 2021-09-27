package com.user.gograb.adapter.interfaces;

import com.user.gograb.services.model.CartDetailModel;

public interface OnCartInvokeListener {
    //void OnDeleteInvoked(int position, TrendingMenuModel wishModel, String count);
    void OnCartListListener(CartDetailModel position, String wishlistPostModel);
}
