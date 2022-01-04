package com.oms.service;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oms.bean.Customer;
import com.oms.db.IMediaDatabase;
import com.oms.db.JsonMediaDatabase;

@Path("/customers")
public class CustomerService {

	private IMediaDatabase customerDatabase;

	public CustomerService() {
		customerDatabase = JsonMediaDatabase.singleton();
	}

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public ArrayList<Customer> getAllCustomer() {
	     ArrayList<Customer> res = customerDatabase.searchCustomer(null);
	     return res;
	 }

}