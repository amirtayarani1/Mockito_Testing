package com.amir.order.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.amir.order.bo.exception.BOException;
import com.amir.order.dao.OrderDAO;
import com.amir.order.dto.Order;

public class OrderBOImplTest {

	private static final int ORDER_ID = 123;
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {

		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		//when(dao.create(any(Order.class))).thenReturn(new Integer(1)); Mockito method
		boolean result = bo.placeOrder(order);

		assertTrue(result);
		verify(dao).create(order);
		
		// A method how many times will run use "times" ans "atLeast" and pass the number as you need
		//verify(dao, times(2)).create(order);
		//verify(dao, atLeast(2)).create(order);

	}

	@Test
	public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {

		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);

		assertFalse(result);
		verify(dao).create(order);

	}

	@Test(expected = BOException.class)
	public void placeOrder_Should_throw_BOException() throws SQLException, BOException {

		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		boolean result = bo.placeOrder(order);

	}

	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(123);

		assertTrue(result);

		verify(dao).read(123);
		verify(dao).update(order);
	}

	@Test
	public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(123);

		assertFalse(result);

		verify(dao).read(123);
		verify(dao).update(order);
	}

	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_Exception_On_Read() throws SQLException, BOException {

		// anyInt or ..  can be used instead of specific numbers
		when(dao.read(anyInt())).thenThrow(SQLException.class);
		bo.cancelOrder(123);
		

	}

	@Test(expected=BOException.class)
	public void cancelOrder_Should_Throw_A_BOException_On_Updat() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(123);

			}
	
	@Test
	public void deleteOrder_Delete_The_Order() throws SQLException, BOException{
		when(dao.delete(ORDER_ID)).thenReturn(1);
		boolean result = bo.deleteOrder(123);
		
		assertTrue(result);
				
		verify(dao).delete(ORDER_ID);
		
	}
}
