package com.service;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bean.DockingStation;
import com.db.IMediaDatabase;
import com.db.JsonMediaDatabase;

@Path("/stations")
public class DockingStationService {

	private IMediaDatabase dockingStationDatabase;

	public DockingStationService() {
		dockingStationDatabase = JsonMediaDatabase.singleton();
	}

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public ArrayList<DockingStation> getAllStations() {
	     ArrayList<DockingStation> res = dockingStationDatabase.searchDockingStation(null);
	     return res;
	 }
	 @POST
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public DockingStation  updateDockingStation(@PathParam("id") String id, DockingStation station) {
		 return dockingStationDatabase.updateDockingStation(station);
	}
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public DockingStation addDockingStation(DockingStation station) {
		return dockingStationDatabase.addDockingStation(station);
	}
	
}