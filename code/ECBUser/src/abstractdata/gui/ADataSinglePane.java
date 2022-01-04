package abstractdata.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import other.StringStyle;

@SuppressWarnings("serial")
public abstract class ADataSinglePane<T> extends JPanel{
	protected T t;
	
	protected GridBagLayout layout;
	protected GridBagConstraints gridBagConstraints = new GridBagConstraints();
	
	protected JPanel actionPanel;
	protected JPanel imgPanel;
	protected JPanel inforPanel;
	
	public ADataSinglePane() {
		imgPanel = new JPanel();
		inforPanel = new JPanel();
		
		addComponent(imgPanel, 0, 0);
		addComponent(inforPanel, 1, 0);
		
		setBorder(new LineBorder(Color.black, 3));

		inforPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		buildControls();
	}
	
	public ADataSinglePane(T t) {
		this();
		this.t = t;
		displayData();
	}
	
	public void buildControls() {
		layout = new GridBagLayout();
		this.setLayout(layout);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
	}
	public void addDataHandlingComponent(Component component) {
		if (actionPanel == null) {
			int row = getLastRowIndex();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = row;
			actionPanel = new JPanel();
			this.add(actionPanel, gridBagConstraints);
			actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		actionPanel.add(component);
	}
	
	public void addComponent(Component component, int x, int y) {
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		add(component, gridBagConstraints);
	}
	
	public void addComponent(Component component, int x, int y, int weightx, int weighty) {
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.weightx = weightx;
		add(component, gridBagConstraints);
	}
	
	public T getData() {
		return t;
	}
	
	public abstract void displayData();
	
	public void updateData(T t) {
		this.t = t;
		displayData();
	}
	
	protected String buildText(Object name, Object value) {
		return StringStyle.buildText(name, value, "black");
	}
	
	protected String buildText(Object name, Object value, String color) {
		return StringStyle.buildText(name, value, color);
	}
	
	protected void appendComponent(Component component) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = getLastRowIndex();
		add(component, gridBagConstraints);
	}
	
	protected int getLastRowIndex() {
		layout.layoutContainer(this);
		int[][] dim = layout.getLayoutDimensions();
	    int rows = dim[1].length;
	    return rows;
	}
	
	
	
	
}
