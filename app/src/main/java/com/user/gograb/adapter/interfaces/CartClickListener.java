package com.user.gograb.adapter.interfaces;

import com.user.gograb.services.model.MenuCatModel;
import com.user.gograb.services.model.TrendingMenuModel;

public interface CartClickListener {
    //void OnDeleteInvoked(int position, TrendingMenuModel wishModel, String count);
    void OnCartInvoked(TrendingMenuModel wishModel, String count);
    void OnCategoryCartInvoked(MenuCatModel wishModel, String count);
}
