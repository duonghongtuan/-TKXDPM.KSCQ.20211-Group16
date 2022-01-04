package com.oms.service;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oms.bean.Bike;
import com.oms.bean.Order;
import com.oms.db.IMediaDatabase;
import com.oms.db.JsonMediaDatabase;

@Path("/bikes")
public class BikeService {

	private IMediaDatabase bikeDatabase;

	public BikeService() {
		bikeDatabase = JsonMediaDatabase.singleton();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bike> getAllBikes() {
		ArrayList<Bike> res = bikeDatabase.searchBike(null);
		return res;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Bike addBike(Bike bike) {
		return bikeDatabase.addBike(bike);
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike updateBike(@PathParam("id") String id, Bike bike) {
		return bikeDatabase.updateBike(bike);
	}
}