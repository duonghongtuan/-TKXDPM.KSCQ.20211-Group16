package abstractdata.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import abstractdata.controller.ADataPageController;

@SuppressWarnings("serial")
public abstract class ADataListPane<T> extends JScrollPane {
	protected LayoutManager layout;
	protected JPanel pane;
	
	protected ADataPageController<T> controller;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	public ADataListPane() {
		pane = new JPanel();
		GridLayout layout = new GridLayout(0, 2);
		layout.setVgap(10);
		layout.setHgap(10);
		setPaneLayout(layout);
		
		this.setViewportView(pane);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getVerticalScrollBar().setUnitIncrement(20);
		this.getHorizontalScrollBar().setUnitIncrement(20);
	}
	
	public void setPaneLayout(LayoutManager layout) {
		this.layout = layout;
		pane.setLayout(layout);
	}
	

	public void displayData(List<? extends T> list) {
		for (T t: list) {
			ADataSinglePane<T> singlePane = createSinglePane();
			singlePane.updateData(t);
			pane.add(singlePane);
		}
	}
	
	public ADataSinglePane<T> createSinglePane(){
		ADataSinglePane<T> singlePane = controller.createSinglePane();
		decorateSinglePane(singlePane);
		return singlePane;
	}
	
	public abstract void decorateSinglePane(ADataSinglePane<T> singlePane);

	public void updateData(List<? extends T> list) {
		pane.removeAll();
		pane.revalidate();
		pane.repaint();
		displayData(list);
	}
}
