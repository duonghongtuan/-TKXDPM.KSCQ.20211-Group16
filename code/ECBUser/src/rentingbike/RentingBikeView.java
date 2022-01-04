package rentingbike;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ecb.bean.Bike;
import com.ecb.bean.Order;

import api.EcoBikeApi;

public class RentingBikeView extends JDialog {
	private final JPanel contentPanel;
	private JPanel rentingBikePanel;
	
	public RentingBikeView(SearchPanel searchPanel, DataPanel dataPanel) {
		contentPanel = new JPanel();
		buildControl();
		if (searchPanel!=null) rentingBikePanel.add(searchPanel);
		rentingBikePanel.add(dataPanel);
	}
	public RentingBikeView(DataPanel dataPanel) {
		contentPanel = new JPanel();
		buildControl();
		rentingBikePanel.add(dataPanel);
	}
	public void buildControl() {
		setResizable(false);
		setTitle("Thuê xe");
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		rentingBikePanel = new JPanel();
		rentingBikePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		rentingBikePanel.setBounds(10, 10, 760, 445);
		contentPanel.add(rentingBikePanel);
		rentingBikePanel.setLayout(null);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(this.getClass().getResource("../img/bike.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
			imgPanel.setBounds(5, 5, 130, 120);
			rentingBikePanel.add(imgPanel);
			imgPanel.setLayout(new BorderLayout());
			{
				JLabel imgLabel = new JLabel("");
				imgLabel.setBounds(10, 11, 106, 66);
				Image dimg = myPicture.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),
				        Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				imgLabel.setIcon(imageIcon);	
				imgLabel.setVerticalAlignment(JLabel.CENTER);
				imgLabel.setHorizontalAlignment(JLabel.CENTER);
				imgPanel.add(imgLabel);
			}
		}
		
		JLabel title = new JLabel("Thuê xe");
		
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(title);	
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 50));
		
		rentingBikePanel.add(titlePanel);
		titlePanel.setBounds(140,5,610,120);
		titlePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(title);
		
		
	}
	
//	public RentingBike(String bikeCode) {
//		this();
//		inputCodeField.setText(bikeCode);
//		codeButton.doClick();
//	}
//	
//	public RentingBike(Bike bike) {
//		this();
//	}
//	
//	public void showNotification(String content){
//			notificationLabel.setText(content);
//	}
}
