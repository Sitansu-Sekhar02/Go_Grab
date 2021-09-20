package com.sauthi.gograb.adapter.interfaces;

import com.sauthi.gograb.services.model.OrderModel;

public interface ReOrderClickInvoke {
    void OnReOrderInvoked(int position, OrderModel orderModel);
}
