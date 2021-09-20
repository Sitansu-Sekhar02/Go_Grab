package com.sauthi.gograb.adapter.interfaces;


import com.sauthi.gograb.services.model.HomeTopCategoryModel;
import com.sauthi.gograb.services.model.HomeSubCategoryModel;

public interface OnWishlistClickInvoke {

   // void OnClickInvoke( HomeSubCategoryModel model);

    void OnClickInvoke(int position, HomeTopCategoryModel model);
    void OnSubCategoryClickInvoke(int position, HomeSubCategoryModel model);
}
