package viewstation;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;

import abstractdata.controller.ADataPageController;
import abstractdata.gui.ADataListPane;
import abstractdata.gui.ADataPagePane;
import abstractdata.gui.ADataSinglePane;
import other.StringStyle;
import viewbike.UserBikePageController;

public class StationListPane extends ADataListPane<DockingStation>{
	
	public StationListPane(ADataPageController<DockingStation> controller) {
		this.controller = controller;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void displayData(java.util.List<? extends DockingStation> list) {
		pane.setLayout(layout);
		if(list.isEmpty()) {
			JPanel p = new JPanel();
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
			JLabel label = new JLabel("Không tìm thấy!");
			label.setForeground(Color.red);
			label.setFont(StringStyle.BIG_FONT);
			label.setAlignmentX(900);
//			label.setBorder(new EmptyBorder(0, this.getWidth()/2-40, 0, 0));
			pane.setLayout(new GridBagLayout());
			pane.add(label);
//			p.setBackground(Color.red);
			
		}
		else
			super.displayData(list);
	}
	

	@Override
	public void decorateSinglePane(ADataSinglePane<DockingStation> singlePane) {
		
		Button button = new Button("Danh sách xe");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				((UserStationPageController)controller).onViewStationDetail(singlePane);
			}
		});;
		singlePane.addDataHandlingComponent(button);
		// TODO Auto-generated method stub
	}
	

}
