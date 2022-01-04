package com.oms.service;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.oms.bean.Order;
import com.oms.db.IMediaDatabase;
import com.oms.db.JsonMediaDatabase;

@Path("/orders")
public class OrderService {

	private IMediaDatabase orderDatabase;

	public OrderService() {
		orderDatabase = JsonMediaDatabase.singleton();
	}

	@GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public ArrayList<Order> getAllOrders() {
	     ArrayList<Order> res = orderDatabase.searchOrder(null);
	     return res;
	 }
	 
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Order addOrder(Order order) {
		return orderDatabase.addOrder(order);
	}
	 
	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Order updateOrder(@PathParam("id") String id, Order order) {
		return orderDatabase.updateOrder(order);
	}
	
	 @POST
	 @Path("/delete/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Order delete(@PathParam("id") String id, Order order) {
	    return orderDatabase.deleteOrder(order);
	 }
}