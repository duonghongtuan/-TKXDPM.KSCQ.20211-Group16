package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import other.SourcePath;
import other.StringStyle;


public class HomeHeader extends JPanel {
	
	private JLabel label;
	private JLabel lblEcobike;
	private JPanel mainPane;
	private JPanel buttonPane;
	
	public HomeHeader(String customer) {
		setLayout(new BorderLayout());
		
		buttonPane = new JPanel();
		mainPane = new JPanel();

		lblEcobike = new JLabel("EcoBike");
		lblEcobike.setIcon(SourcePath.getIcon("bike"));
		lblEcobike.setFont(StringStyle.ICON_FONT);
		lblEcobike.setBorder(new EmptyBorder(20, 80, 0, 0));
		
		add(lblEcobike, BorderLayout.WEST);
		add(mainPane, BorderLayout.EAST);
		
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		
//		label = new JLabel(customer);
//		label.setIcon(SourcePath.getIcon("user"));
//		label.setFont(StringStyle.BIG_FONT);
//		label.setBorder(new EmptyBorder(10, 0, 10, 100));
//		
//		mainPane.add(label);
		mainPane.add(buttonPane);
		mainPane.setBorder(new EmptyBorder(20, 80, 0, 0));
		
		setUpButtonPane();
		
	}
	
	public void setUpButtonPane() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		buttonPane.setLayout(flowLayout);
		
		JPanel linePane = new JPanel();
		String temp = "";
		for(int i=0; i< 320; i++) {
			temp += "-";
		}
		JLabel label = new JLabel(temp);
		linePane.add(label);
		add(linePane, BorderLayout.SOUTH);
	}
	
	

	public void addButton(Button component) {
		component.setFont(StringStyle.NORMAL_FONT);
		buttonPane.add(component);
	}
	
	public void addButton(Button component, ActionListener action) {
		component.setFont(StringStyle.NORMAL_FONT);
		component.addActionListener(action);
		buttonPane.add(component);
	}
	
	public void fixButtonStyle() {
		
	}
	
}
