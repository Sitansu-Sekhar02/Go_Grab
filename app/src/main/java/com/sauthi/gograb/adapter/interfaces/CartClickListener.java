package com.sauthi.gograb.adapter.interfaces;

import com.sauthi.gograb.services.model.MenuCatModel;
import com.sauthi.gograb.services.model.TrendingMenuModel;

public interface CartClickListener {
    //void OnDeleteInvoked(int position, TrendingMenuModel wishModel, String count);
    void OnCartInvoked(TrendingMenuModel wishModel, String count);
    void OnCategoryCartInvoked(MenuCatModel wishModel, String count);
}
