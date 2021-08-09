package com.sa.gograb.adapter.interfaces;


import com.sa.gograb.services.model.RestaurantModel;
import com.sa.gograb.services.model.WishModel;

public interface FavouriteListClickListener {
    void OnFavouriteClickListener(int position, WishModel wishModel);
}
