package com.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.service.BikeService;
import com.service.CardService;
import com.service.CustomerService;
import com.service.DockingStationService;
import com.service.OrderService;

public class OMSServer {
	public static final int PORT = 8080;
	
	public static void main(String[] args) throws Exception {
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		Server jettyServer = new Server(PORT);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				BikeService.class.getCanonicalName() + ", " +
			    DockingStationService.class.getCanonicalName() + ", " +
			    OrderService.class.getCanonicalName()  + ", " +
			    CardService.class.getCanonicalName()   + ", " +
				CustomerService.class.getCanonicalName()
		);

		
		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}
}