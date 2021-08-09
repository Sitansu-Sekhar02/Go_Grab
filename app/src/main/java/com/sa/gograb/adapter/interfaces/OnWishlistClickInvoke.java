package com.sa.gograb.adapter.interfaces;


import com.sa.gograb.services.model.HomeTopCategoryModel;
import com.sa.gograb.services.model.HomeSubCategoryModel;
import com.sa.gograb.services.model.RestaurantModel;

public interface OnWishlistClickInvoke {

   // void OnClickInvoke( HomeSubCategoryModel model);

    void OnClickInvoke(int position, HomeTopCategoryModel model);
    void OnSubCategoryClickInvoke(int position, HomeSubCategoryModel model);
}
