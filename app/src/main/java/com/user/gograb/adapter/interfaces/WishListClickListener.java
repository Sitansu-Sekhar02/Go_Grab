package com.user.gograb.adapter.interfaces;


import com.user.gograb.services.model.RestaurantModel;

public interface WishListClickListener {
    void OnRestaurantClickInvoke(int position, RestaurantModel restaurantModel);
}
