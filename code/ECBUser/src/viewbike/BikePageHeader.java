package viewbike;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ecb.bean.DockingStation;

import abstractdata.gui.AHeaderPane;
import other.Constants;
import other.StringStyle;

public class BikePageHeader extends AHeaderPane{
	
	private JLabel labelStation;
	private JLabel labelAddress;
	private JLabel labelNoOfBike;
	private JLabel labelNoOfEBike;
	private JLabel labelNoOfTWinBike;
	
	public BikePageHeader(DockingStation station) {
		// TODO Auto-generated constructor stub
		labelStation = new JLabel();
		labelStation.setFont(StringStyle.BIG_FONT);
		labelAddress = new JLabel();
		
		int bike=0, ebike=0, twinbike=0;
		
		for (com.ecb.bean.Bike _bike: station.getListBike()) {
			if(_bike.getType() == Constants.BIKE.bike)
				bike ++;
			if(_bike.getType() == Constants.BIKE.ebike)
				ebike ++;
			if(_bike.getType() == Constants.BIKE.twinBike)
				twinbike ++;
		}
		
		labelNoOfBike = new JLabel("Số xe đạp đơn thường (Bike): " + bike);
		labelNoOfBike.setFont(StringStyle.NORMAL_FONT);
		labelNoOfEBike = new JLabel("Số xe đạp đơn điện (EBike): " + ebike);
		labelNoOfEBike.setFont(StringStyle.NORMAL_FONT);
		labelNoOfTWinBike = new JLabel("Số xe đạp đôi thường (TWinBike): " + twinbike);
		labelNoOfTWinBike.setFont(StringStyle.NORMAL_FONT);
		labelStation.setText(station.getName());
		labelAddress.setText("Địa chỉ: "+station.getAddress());
		labelAddress.setFont(StringStyle.NORMAL_FONT);

		setLayout(new BorderLayout());
//		add(getStationPane());
//		add(getBikeQuantityPane());
		
		JPanel panel = getBikeQuantityPane();
		JPanel stationPane = getStationPane();
		stationPane.setBorder(new EmptyBorder(0, 300, 0, 0));
		add(stationPane, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(0, 0, 0, 30));
		add(panel, BorderLayout.EAST);
	}
	
	public JPanel getBikeQuantityPane() {
		JPanel pane = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		pane.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
	
		pane.add(labelNoOfBike, gridBagConstraints);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		pane.add(labelNoOfEBike, gridBagConstraints);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		pane.add(labelNoOfTWinBike, gridBagConstraints);
		return pane;
	}
	
	public JPanel getStationPane() {
		JPanel pane = new JPanel() {
			@Override
			public void paint(Graphics arg0) {
				setFont(StringStyle.BIG_FONT);
				super.paint(arg0);
			}
		};
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		pane.add(labelStation, gridBagConstraints);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		pane.add(labelAddress, gridBagConstraints);
		return pane;
	}
	
	 private GridBagConstraints createGbc(int x, int y) {
	      GridBagConstraints gbc = new GridBagConstraints();
	      gbc.gridx = x;
	      gbc.gridy = y;
	      gbc.gridwidth = 1;
	      gbc.gridheight = 1;

	      gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
	      gbc.fill = (x == 0) ? GridBagConstraints.BOTH
	            : GridBagConstraints.HORIZONTAL;

//	      gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
	      gbc.weightx = (x == 0) ? 0.1 : 1.0;
	      gbc.weighty = 1.0;
	      return gbc;
	   }
	
	
	
	
	
	
	
}
