package returnbike;

import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;
import com.ecb.bean.Order;
import javax.swing.UIManager;

public class ReturnBikeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ReturnBikeController returnBikeController;


	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReturnBikeDialog(Bike bike, DockingStation startStation, HashMap<String, String> stations, Order order) {
		setTitle("Trả xe");
		setBounds(100, 100, 536, 287);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(null);

			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
			panel_1.setBounds(0, 36, 510, 202);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JLabel bikeNameLabel = new JLabel(bike.getName());
			bikeNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
			bikeNameLabel.setBounds(147, 10, 232, 25);
			panel_1.add(bikeNameLabel);

			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
			panel_3.setBounds(12, 11, 125, 139);
			panel_1.add(panel_3);
			panel_3.setLayout(null);

			// load img
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(this.getClass().getResource("../img/bike.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			JLabel bikeImgLabel = new JLabel("");
			bikeImgLabel.setBounds(21, 50, 78, 47);

			Image dimg = myPicture.getScaledInstance(bikeImgLabel.getWidth(), bikeImgLabel.getHeight(),
					Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			bikeImgLabel.setIcon(imageIcon);
			panel_3.add(bikeImgLabel);

			JLabel hiredFromLabel = new JLabel("Nơi thuê:");
			hiredFromLabel.setBounds(147, 48, 45, 14);
			panel_1.add(hiredFromLabel);

			JLabel hiredFromValue = new JLabel(startStation.getName());
			hiredFromValue.setBounds(273, 48, 228, 14);
			panel_1.add(hiredFromValue);

			JPanel panel_4 = new JPanel();
			panel_4.setBackground(UIManager.getColor("Button.shadow"));
			panel_4.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
			panel_4.setBounds(0, 161, 509, 41);
			panel_1.add(panel_4);
			panel_4.setLayout(null);

			JButton paymentButton = new JButton("Thanh toán");
			paymentButton.setBounds(379, 6, 120, 30);
			panel_4.add(paymentButton);

			JButton detailBikeButton = new JButton("Tình trạng xe");
			detailBikeButton.addActionListener(new ActionListener() {
				// Hien thi dialog
				public void actionPerformed(ActionEvent e) {
					returnBikeController.showInfoBikeDialog();
				}
			});
			detailBikeButton.setBounds(249, 6, 120, 30);
			panel_4.add(detailBikeButton);
			

			JLabel hiredTimeFromLabel = new JLabel("Bắt đầu thuê lúc");
			hiredTimeFromLabel.setBounds(147, 68, 80, 14);
			panel_1.add(hiredTimeFromLabel);

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			JLabel hiredTimeFromValue = new JLabel(df.format(order.getStartTime()));
			hiredTimeFromValue.setBounds(273, 68, 228, 14);
			panel_1.add(hiredTimeFromValue);

			JLabel bikeCodeLabel = new JLabel("Mã xe");
			bikeCodeLabel.setBounds(147, 87, 29, 14);
			panel_1.add(bikeCodeLabel);

			JLabel bikeCodeValue = new JLabel(bike.getBikeId());
			bikeCodeValue.setBounds(273, 87, 228, 14);
			panel_1.add(bikeCodeValue);

			JLabel hiredTimeLabel = new JLabel("Thời gian đã thuê");
			hiredTimeLabel.setBounds(147, 106, 85, 14);
			panel_1.add(hiredTimeLabel);

			long millis = Math.abs(order.getFinishTime().getTime() - order.getStartTime().getTime());

			long hours = TimeUnit.MILLISECONDS.toHours(millis);
			millis -= TimeUnit.HOURS.toMillis(hours);
			long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
			JLabel hiredTimeValue = new JLabel(String.valueOf(hours) + " giờ " + String.valueOf(minutes) + " phút");
			hiredTimeValue.setBounds(273, 106, 228, 14);
			panel_1.add(hiredTimeValue);
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(273, 125, 227, 22);
			panel_1.add(comboBox);
			comboBox.setModel(new DefaultComboBoxModel(stations.keySet().toArray()));

			JLabel lblChnVTr = new JLabel("Chọn vị trí trả xe");
			lblChnVTr.setBounds(147, 128, 85, 14);
			panel_1.add(lblChnVTr);

			JLabel titleHiredBikeLabel_1 = new JLabel("Home >");
			titleHiredBikeLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			titleHiredBikeLabel_1.setBounds(10, 8, 53, 20);
			panel.add(titleHiredBikeLabel_1);

			JLabel titleHiredBikeLabel_1_1 = new JLabel("Trả xe");
			titleHiredBikeLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
			titleHiredBikeLabel_1_1.setBounds(64, 8, 42, 20);
			panel.add(titleHiredBikeLabel_1_1);
			
			// Goi sang thanh toan
			paymentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					returnBikeController.onPayment(stations.get(comboBox.getSelectedItem()));
				}
			});
		}
	}

	public void setReturnBikeController(ReturnBikeController returnBikeController) {
		this.returnBikeController = returnBikeController;
	}
	
}
