package com.sa.gograb.adapter.interfaces;


import com.sa.gograb.services.model.RestaurantModel;
import com.sa.gograb.services.model.WishListModel;

public interface WishListClickListener {
    void OnRestaurantClickInvoke(int position, RestaurantModel restaurantModel);
}
