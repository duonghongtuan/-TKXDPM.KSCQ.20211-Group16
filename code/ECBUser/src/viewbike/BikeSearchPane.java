package viewbike;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

import abstractdata.controller.IDataSearchController;
import abstractdata.gui.ADataSearchPane;
import other.Constants;
import other.StringStyle;

public class BikeSearchPane extends ADataSearchPane{
	
	JComboBox<String> typeBike;
	
	public BikeSearchPane() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void buildControls() {
		// TODO Auto-generated method stub
		typeBike = new JComboBox<String>(Constants.BIKE.bikeType);
		typeBike.setFont(StringStyle.NORMAL_FONT);
		typeBike.setSelectedIndex(Constants.BIKE.bikeType.length-1);
		c.gridx = 1;
		c.gridy = getLastRowIndex() - 1;
		add(typeBike, c);
		
	}
	
	@Override
	public Map<String, String> getQueryParams() {
		// TODO Auto-generated method stub
		Map<String, String> res = super.getQueryParams();
		res.put("type", typeBike.getSelectedItem().toString());
		return res;
	}

}
