package payment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private OrderInfoPanel orderInfoPanel;
	private PaymentInfoPanel paymentInfoPanel;

	public PaymentDialog(OrderInfoPanel orderInfoPanel, PaymentInfoPanel paymentInfoPanel) {
		super();
		buildControl();
		this.orderInfoPanel = orderInfoPanel;
		this.paymentInfoPanel = paymentInfoPanel;
		contentPanel.add(orderInfoPanel);
		contentPanel.add(paymentInfoPanel);
	}


	/**
	 * Create the dialog.
	 */
	private void buildControl() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Thanh toán");
		setBounds(100, 100, 796, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	public void update(Bike bike, DockingStation startStation, DockingStation endStation) {
		orderInfoPanel.update(bike, startStation, endStation);
		paymentInfoPanel.update();
	}
}
