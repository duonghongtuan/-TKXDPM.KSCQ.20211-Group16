package com.oms.db;

import java.util.ArrayList;

import com.oms.bean.Order;
import com.oms.bean.Bike;
import com.oms.bean.CreditCard;
import com.oms.bean.Customer;
import com.oms.bean.DockingStation;

public interface IMediaDatabase {
	
	
	public ArrayList<Bike> searchBike(Bike bike);
	public Bike updateBike(Bike bike);
	public Bike addBike(Bike bike);
	public ArrayList<DockingStation> searchDockingStation(DockingStation station);
	
	
	public DockingStation updateDockingStation(DockingStation station);
	public DockingStation addDockingStation(DockingStation station);
	public void deleteDockingStation(DockingStation station);
	
	public ArrayList<Order> searchOrder(Order order);
	public Order updateOrder(Order order);
	public Order addOrder (Order order);
	public Order deleteOrder(Order order);
	public boolean checkOutOrder(Order order);
	
	public CreditCard searchCard(String cardId);
	public double plusAmount(String cartId,double money);
	public double minusAmount(String cartId,double money);
	public ArrayList<Customer> searchCustomer(Customer customer);
}
