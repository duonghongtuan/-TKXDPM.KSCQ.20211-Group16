package main;


import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JPanel;

import abstractdata.gui.ADataPagePane;
import rentingbike.RentingBikeController;
import returnbike.ReturnBikeController;
import viewstation.UserStationPageController;

public class ECBUserController {
	
	private ECBUser ecbUser;
	@SuppressWarnings("unused")
	private RentingBikeController rentingBikeController;
	private ReturnBikeController returnBikeController;
	private Stack<JPanel> pageStack = new Stack<JPanel>();
	
	public ECBUserController(ECBUser ecbUser) {
		// TODO Auto-generated constructor stub
		this.ecbUser = ecbUser;
	//	rentingBikeController = new RentingBikeController();
		returnBikeController = new ReturnBikeController();
	}
	
	public JPanel getStationPagePane() {
		return new UserStationPageController(this).getDataPagePane();
	}
	
	public void previousPage() {
		if(!pageStack.isEmpty()) {
			if(pageStack.size() > 1)
				pageStack.pop();
			ecbUser.removeAllPages();
			ecbUser.mainPane.add(pageStack.peek());
		}
		
	}
	
	private void reloadCurrentPage() {
		if(pageStack.peek() instanceof ADataPagePane<?>) {
			ADataPagePane<?> x = (ADataPagePane<?>) pageStack.peek();
			x.getSearchPane().fireSearchEvent();
		}
	}
	
	public JPanel getHeader() {
		@SuppressWarnings("static-access")
		HomeHeader homeHeader = new HomeHeader(ecbUser.customer.getFullName());
		
		Runnable callback = new Runnable() {
			
			@Override
			public void run() {
				reloadCurrentPage();
			}
		};
		
		homeHeader.addButton(new Button("Trang chủ"), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goPage(getStationPagePane());
			}
		});
		
		homeHeader.addButton(new Button("Thuê xe"), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RentingBikeController rentingBikeController = new RentingBikeController();
				rentingBikeController.setFinishedEventCallback(callback);
			}
		});
		
		homeHeader.addButton(new Button("Trả xe"), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReturnBikeController returnBikeController = new ReturnBikeController();
				returnBikeController.setFinishedEventCallback(callback);
				returnBikeController.showChooseBillDialog();
			}
		});
		
		return homeHeader;
	}
	
	public void goPage(JPanel panel) {
		pageStack.add(panel);
		ecbUser.removeAllPages();
		ecbUser.mainPane.add(panel);
		ecbUser.mainPane.repaint();
	}
}
