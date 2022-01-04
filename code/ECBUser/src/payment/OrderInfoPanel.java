package payment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;

@SuppressWarnings("serial")
public class OrderInfoPanel extends JPanel {
	
	private PaymentController paymentController;
	private JLabel bikeCodeValue;
	private JLabel bikeName;
	private JLabel hireFromValue;
	private JLabel startHireValue;
	private JLabel returnToValue;
	private JLabel timeReturnValue;
	private JLabel totalHiredTime;
	private SimpleDateFormat df;
	

	public OrderInfoPanel() {
		super();
		buildControl();
	}

	/**
	 * Create the panel.
	 */
	private void buildControl() {
		df = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setBounds(10, 11, 760, 147);
		setLayout(null);
		
		//load bike image
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(this.getClass().getResource("../img/bike.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create label contain image
		JLabel imgLabel = new JLabel("");
		imgLabel.setBounds(10, 11, 106, 66);
		Image dimg = myPicture.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		imgLabel.setIcon(imageIcon);
		
		//panel that wrap the image label
		JPanel imgPanel = new JPanel();
		imgPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		imgPanel.setBounds(10, 25, 125, 88);
		imgPanel.setLayout(null);
		imgPanel.add(imgLabel);
		
		this.add(imgPanel);

		//bike code
		JLabel bikeCodeLabel = new JLabel("Mã xe:");
		bikeCodeLabel.setBounds(20, 124, 33, 14);
		this.add(bikeCodeLabel);

		bikeCodeValue = new JLabel();
		bikeCodeValue.setBounds(64, 124, 202, 14);
		this.add(bikeCodeValue);

		//bike name
		bikeName = new JLabel();
		bikeName.setFont(new Font("Tahoma", Font.BOLD, 14));
		bikeName.setBounds(145, 11, 440, 17);
		this.add(bikeName);

		JLabel hireFromLabel = new JLabel("Nơi thuê:");
		hireFromLabel.setBounds(145, 47, 45, 14);
		this.add(hireFromLabel);

		hireFromValue = new JLabel();
		hireFromValue.setBounds(218, 47, 224, 14);
		this.add(hireFromValue);

		//start hired time
		JLabel startHireLabel = new JLabel("Bắt đầu thuê:");
		startHireLabel.setBounds(145, 73, 67, 14);
		this.add(startHireLabel);

		startHireValue = new JLabel();
		startHireValue.setBounds(218, 73, 224, 14);
		this.add(startHireValue);

		//return dock
		JLabel returnToLabel = new JLabel("Nơi trả:");
		returnToLabel.setBounds(452, 47, 36, 14);
		this.add(returnToLabel);

		returnToValue = new JLabel();
		returnToValue.setBounds(538, 47, 212, 14);
		this.add(returnToValue);

		//finish time
		JLabel timeReturnLabel = new JLabel("Thời gian trả:");
		timeReturnLabel.setBounds(452, 73, 65, 14);
		this.add(timeReturnLabel);

		timeReturnValue = new JLabel();
		timeReturnValue.setBounds(538, 73, 212, 14);
		this.add(timeReturnValue);

		//total hired time
		JLabel timeHiredLabel = new JLabel("Đã thuê:");
		timeHiredLabel.setBounds(145, 98, 43, 14);
		this.add(timeHiredLabel);


		totalHiredTime = new JLabel();
		totalHiredTime.setBounds(218, 98, 224, 14);
		this.add(totalHiredTime);
	}

	/**
	 * update view
	 */
	public void update(Bike bike, DockingStation startStation, DockingStation endStation) {
		bikeCodeValue.setText(bike.getBikeId());
		bikeName.setText(bike.getName());
		hireFromValue.setText(startStation.getName());
		returnToValue.setText(endStation.getName());
		startHireValue.setText(df.format(paymentController.getOrder().getStartTime()));
		timeReturnValue.setText(df.format(paymentController.getOrder().getFinishTime()));
		
		//total hired time
		long millis = Math.abs(paymentController.getOrder().getFinishTime().getTime() - paymentController.getOrder().getStartTime().getTime());

		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		totalHiredTime.setText(String.valueOf(hours) + " giờ " + String.valueOf(minutes) + " phút");
	}
	
	
	/**
	 * get, set
	 */
	public void setPaymentController(PaymentController paymentController) {
		this.paymentController = paymentController;
	}
}
