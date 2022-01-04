package payment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import com.ecb.bean.CreditCard;
import com.ecb.bean.DockingStation;

import api.EcoBikeApi;
import api.EcoBikeApiFactory;


@SuppressWarnings("serial")
public class PaymentInfoPanel extends JPanel {

	private PaymentController paymentController;
	private double total;
	private long millis, hours, minutes;
	private DecimalFormat decimalFormat;

	public PaymentInfoPanel() {
		super();
		decimalFormat = new DecimalFormat("#.##");
		decimalFormat.setGroupingUsed(true);
		decimalFormat.setGroupingSize(3);
		
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.setBounds(10, 169, 760, 237);
		this.setLayout(null);
	}

	public void update() {
		// total hired time
		millis = paymentController.getTimeRent();
	
		hours = TimeUnit.MILLISECONDS.toHours(millis);
		minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		
		total = paymentController.getTotalCost(paymentController.getBike().getType(), minutes);

		buildControl();
	}

	/**
	 * Create the panel.
	 */
	private void buildControl() {
		removeAll();

		JLabel paymentHeader = new JLabel("Thanh toán");
		paymentHeader.setHorizontalAlignment(SwingConstants.CENTER);
		paymentHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		paymentHeader.setBounds(249, 11, 240, 20);
		this.add(paymentHeader);

		// total hired time
		JLabel totalHiredTimeLabel = new JLabel("Thời gian thuê");
		totalHiredTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalHiredTimeLabel.setBounds(189, 48, 68, 14);
		this.add(totalHiredTimeLabel);

		JLabel totalHiredTimeValue = new JLabel();
		totalHiredTimeValue.setText(String.valueOf(hours) + " giờ "
				+ String.valueOf(TimeUnit.MILLISECONDS.toMinutes(millis - TimeUnit.HOURS.toMillis(hours))) + " phút");
		totalHiredTimeValue.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHiredTimeValue.setBounds(130, 158, 129, 14);
		this.add(totalHiredTimeValue);

		JLabel costPerHourLabel = new JLabel("Đơn giá");
		costPerHourLabel.setHorizontalAlignment(SwingConstants.CENTER);
		costPerHourLabel.setBounds(346, 48, 37, 14);
		this.add(costPerHourLabel);

		JLabel paymentTotalLabel = new JLabel("Thanh toán");
		paymentTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paymentTotalLabel.setBounds(506, 48, 55, 14);
		this.add(paymentTotalLabel);

		// calculate price

		if (minutes <= 10) {
			JLabel tenMinutesLabel = new JLabel();
			tenMinutesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			tenMinutesLabel.setBounds(130, 73, 129, 14);
			tenMinutesLabel.setText(String.valueOf(minutes) + " phút");
			this.add(tenMinutesLabel);

			JLabel costPerTimeLabel = new JLabel("0");
			costPerTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			costPerTimeLabel.setBounds(377, 73, 6, 14);
			this.add(costPerTimeLabel);

			JLabel tempSumLabel = new JLabel("0,000");
			tempSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			tempSumLabel.setBounds(445, 73, 116, 14);
			this.add(tempSumLabel);
		} else if (minutes <= 40) {

			JLabel tenMinutesLabel = new JLabel("10 phút");
			tenMinutesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			tenMinutesLabel.setBounds(149, 73, 108, 14);
			this.add(tenMinutesLabel);

			JLabel costPerTimeLabel = new JLabel("0");
			costPerTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			costPerTimeLabel.setBounds(377, 73, 6, 14);
			this.add(costPerTimeLabel);

			JLabel tempSumLabel = new JLabel("0,000");
			tempSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			tempSumLabel.setBounds(445, 73, 116, 14);
			this.add(tempSumLabel);

			JLabel fourtyMinutesLabel = new JLabel();
			fourtyMinutesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			fourtyMinutesLabel.setBounds(159, 98, 98, 14);
			fourtyMinutesLabel.setText(String.valueOf(minutes - 10) + " phút");
			this.add(fourtyMinutesLabel);

			JLabel label_1 = new JLabel("10,000");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(349, 98, 34, 14);
			this.add(label_1);

			JLabel label_2 = new JLabel("10,000");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setBounds(455, 98, 106, 14);
			this.add(label_2);
		} else {
			JLabel tenMinutesLabel = new JLabel("10 phút");
			tenMinutesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			tenMinutesLabel.setBounds(151, 73, 108, 14);
			this.add(tenMinutesLabel);

			JLabel costPerTimeLabel = new JLabel("0");
			costPerTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			costPerTimeLabel.setBounds(312, 73, 75, 14);
			this.add(costPerTimeLabel);

			JLabel tempSumLabel = new JLabel("0,000");
			tempSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			tempSumLabel.setBounds(445, 73, 116, 14);
			this.add(tempSumLabel);

			JLabel fourtyMinutesLabel = new JLabel("30 phút");
			fourtyMinutesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			fourtyMinutesLabel.setBounds(151, 94, 108, 14);
			this.add(fourtyMinutesLabel);

			JLabel label_1 = new JLabel("10,000");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(312, 94, 75, 14);
			this.add(label_1);

			JLabel label_2 = new JLabel("10,000");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setBounds(445, 94, 116, 14);
			this.add(label_2);

			JLabel label_3 = new JLabel();
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			label_3.setBounds(110, 116, 149, 14);
			label_3.setText("(> 40 phút) " + String.valueOf(minutes - 40) + " phút");
			this.add(label_3);

			JLabel lblPht_1_1_1 = new JLabel("3,000 / 15 phút");
			lblPht_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPht_1_1_1.setBounds(312, 116, 75, 14);
			this.add(lblPht_1_1_1);

			JLabel purchaseLabel_3 = new JLabel("0");
			purchaseLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			purchaseLabel_3.setBounds(445, 116, 116, 14);
			if (paymentController.getBike().getType() != 0) {
				purchaseLabel_3.setText(decimalFormat.format(Math.ceil((minutes - 40) * 1.0 / 15) * 3000 * 1.5));
			} else {
				purchaseLabel_3.setText(decimalFormat.format(Math.ceil((minutes - 40) * 1.0 / 15) * 3000));
			}
			this.add(purchaseLabel_3);
		}

		JLabel sumValue = new JLabel();
		sumValue.setText(decimalFormat.format(total));
		sumValue.setHorizontalAlignment(SwingConstants.RIGHT);
		sumValue.setBounds(394, 158, 160, 14);
		this.add(sumValue);

		JSeparator separator = new JSeparator();
		separator.setBounds(189, 141, 365, 4);
		this.add(separator);

		JLabel cardIdLabel = new JLabel("Mã thẻ");
		cardIdLabel.setBounds(230, 193, 33, 14);
		this.add(cardIdLabel);

		JTextField cardIdTextField = new JTextField();
		cardIdTextField.setBounds(286, 190, 165, 20);
		this.add(cardIdTextField);
		cardIdTextField.setColumns(10);

		JButton btnNewButton = new JButton("Check out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentController.checkOut(cardIdTextField.getText(), total);
			}
		});
		btnNewButton.setBounds(461, 189, 83, 23);
		this.add(btnNewButton);
		
		revalidate();
		repaint();
	}

	public void setPaymentController(PaymentController paymentController) {
		this.paymentController = paymentController;
	}


}
