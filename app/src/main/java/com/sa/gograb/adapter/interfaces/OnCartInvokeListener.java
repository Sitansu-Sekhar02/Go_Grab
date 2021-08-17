package com.sa.gograb.adapter.interfaces;

import com.sa.gograb.services.model.CartDetailModel;
import com.sa.gograb.services.model.MenuCatModel;
import com.sa.gograb.services.model.TrendingMenuModel;

public interface OnCartInvokeListener {
    //void OnDeleteInvoked(int position, TrendingMenuModel wishModel, String count);
    void OnCartListListener(CartDetailModel position, String wishlistPostModel);
}
