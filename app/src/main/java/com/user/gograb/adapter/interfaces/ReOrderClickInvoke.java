package com.user.gograb.adapter.interfaces;

import com.user.gograb.services.model.OrderModel;

public interface ReOrderClickInvoke {
    void OnReOrderInvoked(int position, OrderModel orderModel);
}
