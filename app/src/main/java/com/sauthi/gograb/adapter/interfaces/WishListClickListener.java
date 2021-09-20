package com.sauthi.gograb.adapter.interfaces;


import com.sauthi.gograb.services.model.RestaurantModel;

public interface WishListClickListener {
    void OnRestaurantClickInvoke(int position, RestaurantModel restaurantModel);
}
