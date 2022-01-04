package abstractdata.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import abstractdata.controller.IDataSearchController;
import other.StringStyle;

@SuppressWarnings("serial")
public abstract class ADataSearchPane extends JPanel {
	protected GridBagLayout layout;
	protected GridBagConstraints c;
	
	private IDataSearchController controller;

	public ADataSearchPane() {
//		setBackground(Color.red);
		layout = new GridBagLayout();
		this.setLayout(layout);
		c = new GridBagConstraints();
		c.insets = new Insets(0, 20, 10, 0);
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		buildControls();
		
		int row = getLastRowIndex();
		c.gridx = 2;
		c.gridy = row - 1;
		
		
		JButton searchButton = new JButton("Tìm kiếm");
		searchButton.setFont(StringStyle.NORMAL_FONT);
		
		add(searchButton, c);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Search Query: "+getQueryParams());
				controller.search(getQueryParams());
			}
		});
		// Empty label for resizing
	}
	
	public ADataSearchPane(IDataSearchController controller) {
		this();
		this.controller = controller;
	}
	
	public abstract void buildControls();
	
	public Map<String, String> getQueryParams() {
		Map<String, String> res = new HashMap<String, String>();
		return res;
	}
	
	protected int getLastRowIndex() {
		layout.layoutContainer(this);
		int[][] dim = layout.getLayoutDimensions();
	    int rows = dim[1].length;
	    return rows;
	}
	
	
	public void setController(IDataSearchController controller) {
		this.controller = controller;
	}
	
	public void fireSearchEvent() {
		controller.search(getQueryParams());
	}
}
