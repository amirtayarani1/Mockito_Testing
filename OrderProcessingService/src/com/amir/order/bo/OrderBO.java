package com.amir.order.bo;

import com.amir.order.bo.exception.BOException;
import com.amir.order.dto.Order;

public interface OrderBO {

	boolean placeOrder(Order order) throws BOException;

	boolean cancelOrder(int id) throws BOException;

	boolean deleteOrder(int id) throws BOException;

}
