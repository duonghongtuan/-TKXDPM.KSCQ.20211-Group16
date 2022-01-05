package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import com.ecb.bean.Customer;
import api.EcoBikeApi;
import api.EcoBikeApiFactory;

@SuppressWarnings("serial")
public class ECBUser extends JFrame{
	
	public static final int WINDOW_WIDTH = 1300;
	public static final int WINDOW_HEIGHT = 750;
	
	
	JPanel mainPane = new JPanel();
	JPanel ecbPage = new JPanel();
	JPanel actionPane = new JPanel();
	ECBUserController ecbUserController;
	Button previousButton = new Button("Trang trước");
	public static Customer customer = EcoBikeApiFactory.getInstance().getCustomer("customer1");
	
	
	public ECBUser() {
		// TODO Auto-generated constructor stub
		super("Ecobike App");

		ecbUserController = new ECBUserController(this);
		
		setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		setMaximumSize(new Dimension(1200, 900));
		setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT) );
		
		ecbPage = (JPanel)getContentPane();
		ecbPage.setLayout(new BorderLayout());
		ecbPage.add(mainPane);
		ecbPage.add(actionPane, BorderLayout.SOUTH);
		
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		
		actionPane.setLayout(new BorderLayout());
		actionPane.setBorder(new EmptyBorder(0, 0, 5, 20));
		actionPane.add(previousButton, BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ecbUserController.previousPage();
			}
		});

		JLabel labelUser = new JLabel();
		labelUser.setBorder(new EmptyBorder(0, 30, 0, 0));
		labelUser.setText("Copyright © 2021 group-16. All rights reserved.");
		labelUser.setFont(new Font("Time New Roman", Font.BOLD, 13));
		actionPane.add(labelUser, BorderLayout.WEST);
		
		this.setVisible(true);
	}
	
	public void run() {
		JPanel header = ecbUserController.getHeader(); 
		ecbPage.add(header, BorderLayout.NORTH);
		ecbUserController.goPage(ecbUserController.getStationPagePane());
		pack();
	}
	
	
	public void removeAllPages() {
		mainPane.removeAll();
		mainPane.revalidate();
		mainPane.repaint();
	}
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new ECBUser().run();
	}

}
