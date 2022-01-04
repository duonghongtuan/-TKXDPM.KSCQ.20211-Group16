package abstractdata.gui;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import viewbike.BikePageHeader;

@SuppressWarnings("serial")
public class ADataPagePane<T> extends JPanel{
	
	private AHeaderPane headerPane;
	SpringLayout layout = new SpringLayout();
	BoxLayout layout1 = new BoxLayout(this, BoxLayout.Y_AXIS);
	ADataSearchPane searchPane;
	ADataListPane<T> listPane;
	
	public ADataPagePane(AHeaderPane headerPane, ADataSearchPane searchPane, ADataListPane<T> listPane) {
		this.setLayout(layout);
		setHeader(headerPane);
	}
	
	public ADataPagePane(ADataSearchPane searchPane, ADataListPane<T> listPane) {
		this.setLayout(layout);
		this.add(searchPane);
		this.add(listPane);
		this.searchPane = searchPane;
		this.listPane = listPane;
		
		layout.putConstraint(SpringLayout.WEST, searchPane, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, searchPane, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, searchPane, -5, SpringLayout.EAST, this);
		
		paneOneAbovePaneTwo(searchPane, listPane);
		layout.putConstraint(SpringLayout.SOUTH, listPane, -20, SpringLayout.SOUTH, this);
	}
	
	public void setHeader(AHeaderPane header) {
		
		this.headerPane = header;
		this.add(headerPane);
		layout.putConstraint(SpringLayout.WEST, headerPane, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, headerPane, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, headerPane, -5, SpringLayout.EAST, this);
		
		paneOneAbovePaneTwo(header, searchPane);
		paneOneAbovePaneTwo(searchPane, listPane);
		layout.putConstraint(SpringLayout.SOUTH, listPane, -20, SpringLayout.SOUTH, this);

	}
	
	private void paneOneAbovePaneTwo(Component paneOne, Component paneTwo) {
		layout.putConstraint(SpringLayout.WEST, paneTwo, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, paneTwo, 5, SpringLayout.SOUTH, paneOne);
		layout.putConstraint(SpringLayout.EAST, paneTwo, -5, SpringLayout.EAST, this);
	}
	
	public ADataSearchPane getSearchPane() {
		return searchPane;
	}
	
	
	
	
}
