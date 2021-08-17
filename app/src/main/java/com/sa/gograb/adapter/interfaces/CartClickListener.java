package com.sa.gograb.adapter.interfaces;

import com.sa.gograb.services.model.CartDetailModel;
import com.sa.gograb.services.model.MenuCatModel;
import com.sa.gograb.services.model.TrendingMenuModel;

public interface CartClickListener {
    //void OnDeleteInvoked(int position, TrendingMenuModel wishModel, String count);
    void OnCartInvoked(TrendingMenuModel wishModel, String count);
    void OnCategoryCartInvoked(MenuCatModel wishModel, String count);
}
