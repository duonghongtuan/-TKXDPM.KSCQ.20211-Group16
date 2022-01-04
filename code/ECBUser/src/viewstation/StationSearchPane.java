package viewstation;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import abstractdata.gui.ADataSearchPane;
import other.StringStyle;

public class StationSearchPane extends ADataSearchPane{

	JTextField searchBar;
	
	public StationSearchPane() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void buildControls() {
		// TODO Auto-generated method stub
		searchBar = new JTextField("", 20);
		searchBar.setFont(StringStyle.NORMAL_FONT);
		c.gridx = 1;
		c.gridy = getLastRowIndex() - 1;
		add(searchBar, c);
		
	}
	
	@Override
	public Map<String, String> getQueryParams() {
		// TODO Auto-generated method stub
		Map<String, String> res = super.getQueryParams();
		res.put("keyword", searchBar.getText());
		return res;
	}

}
