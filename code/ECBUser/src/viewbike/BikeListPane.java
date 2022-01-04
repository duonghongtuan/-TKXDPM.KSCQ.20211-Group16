package viewbike;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.LayerUI;

import com.ecb.bean.Bike;

import abstractdata.controller.ADataPageController;
import abstractdata.gui.ADataListPane;
import abstractdata.gui.ADataSinglePane;
import other.StringStyle;
import rentingbike.RentingBikeController;

public class BikeListPane extends ADataListPane<Bike> {
	
	public BikeListPane(ADataPageController<Bike> controller) {
		this.controller = controller;
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(getWidth() >= 1300) {
					changeColumnOfLayout(3);
				}else {
					changeColumnOfLayout(2);
				}
				super.componentResized(e);
			}
		});
	}
	
	private void changeColumnOfLayout(int column) {
		if(layout instanceof GridLayout) {
			if(column != ((GridLayout) layout).getColumns()) {
				pane.setLayout(layout);
				((GridLayout) layout).setColumns(column);
				repaint();
				revalidate();
			}
			
		}
	}
	
	@Override
	public void displayData(java.util.List<? extends Bike> list) {
		pane.setLayout(layout);
		if(list.isEmpty()) {
			pane.setLayout(new GridBagLayout());
			JLabel label = new JLabel("Have no bike");
			label.setForeground(Color.red);
			label.setFont(StringStyle.BIG_FONT);
			pane.add(label);
		}
		else
			super.displayData(list);
	}
	
	@Override
	public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
		Button rentButton = new Button("Thuê ngay");
		rentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(singlePane.getData().isRent()) {
					JOptionPane.showMessageDialog(BikeListPane.this, 
							"Xe này đã được thuê. Hãy chọn xe khác!", 
							"EcoBikeRental", 
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					((UserBikePageController) controller).onRent(singlePane);

				}
					
			}
		});
		singlePane.addDataHandlingComponent(rentButton);
	}


}
