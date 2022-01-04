package returnbike;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import com.ecb.bean.Order;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class ChooseBillDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChooseBillDialog(ReturnBikeController returnBikeController) {
		setTitle("Chọn hóa đơn");
		setBounds(100, 100, 346, 188);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Chọn mã xe cần thanh toán");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 310, 25);
		contentPanel.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 107, 330, 42);
		contentPanel.add(panel);
		panel.setLayout(null);


		JButton btnCancel = new JButton("Hủy");
		btnCancel.setBounds(231, 6, 89, 31);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JComboBox comboBoxListBill = new JComboBox();
		comboBoxListBill.setBounds(10, 47, 310, 22);
		contentPanel.add(comboBoxListBill);
		comboBoxListBill.setModel(new DefaultComboBoxModel(returnBikeController.getListOrder().keySet().toArray()));
		
		JButton btnChooseOK = new JButton("OK");
		btnChooseOK.setBackground(Color.WHITE);
		btnChooseOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order order = returnBikeController.getListOrder().get(comboBoxListBill.getSelectedItem());
				dispose();
				returnBikeController.showReturnBikeDialog(order);
			}
		});
		btnChooseOK.setBounds(132, 6, 89, 31);
		panel.add(btnChooseOK);
	}
}
