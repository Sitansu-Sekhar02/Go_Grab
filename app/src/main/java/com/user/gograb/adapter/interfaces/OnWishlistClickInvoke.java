package com.user.gograb.adapter.interfaces;


import com.user.gograb.services.model.HomeTopCategoryModel;
import com.user.gograb.services.model.HomeSubCategoryModel;

public interface OnWishlistClickInvoke {

   // void OnClickInvoke( HomeSubCategoryModel model);

    void OnClickInvoke(int position, HomeTopCategoryModel model);
    void OnSubCategoryClickInvoke(int position, HomeSubCategoryModel model);
}
