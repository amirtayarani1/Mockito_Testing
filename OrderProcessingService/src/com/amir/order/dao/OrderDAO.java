package com.amir.order.dao;

import java.sql.SQLException;

import com.amir.order.dto.Order;

public interface OrderDAO {

	int create(Order order) throws SQLException;

	Order read(int id) throws SQLException;

	int update(Order Order) throws SQLException;

	int delete(int id) throws SQLException;
}
