package returnbike;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.ecb.bean.Bike;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class InfoBikeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public InfoBikeDialog(Bike bike) {
		setTitle("Tình trạng xe");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		panel.setBounds(10, 52, 414, 165);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel nameBikeLabel = new JLabel("Tên xe :");
		nameBikeLabel.setBounds(89, 11, 95, 14);
		panel.add(nameBikeLabel);
		
		JLabel nameBikeValue = new JLabel(bike.getName());
		nameBikeValue.setHorizontalAlignment(SwingConstants.RIGHT);
		nameBikeValue.setBounds(194, 11, 151, 14);
		panel.add(nameBikeValue);
		
		JLabel weightBikeLabel = new JLabel("Trọng lượng (kg) :");
		weightBikeLabel.setBounds(89, 36, 118, 14);
		panel.add(weightBikeLabel);
		
		JLabel weightBikeVaue = new JLabel(String.valueOf(bike.getWeight()));
		weightBikeVaue.setHorizontalAlignment(SwingConstants.RIGHT);
		weightBikeVaue.setBounds(236, 36, 109, 14);
		panel.add(weightBikeVaue);
		
		JLabel lisencePlateLabel = new JLabel("Biển số xe :");
		lisencePlateLabel.setBounds(89, 61, 118, 14);
		panel.add(lisencePlateLabel);
		
		JLabel lisencePlateValue = new JLabel(bike.getLisencePlate());
		lisencePlateValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lisencePlateValue.setBounds(236, 61, 109, 14);
		panel.add(lisencePlateValue);
		
		JLabel createDateLabel = new JLabel("Ngày sản xuất :");
		createDateLabel.setBounds(89, 86, 118, 14);
		panel.add(createDateLabel);
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		JLabel createDateValue = new JLabel(df.format(bike.getManuafaturingDate()));
		createDateValue.setHorizontalAlignment(SwingConstants.RIGHT);
		createDateValue.setBounds(236, 86, 109, 14);
		panel.add(createDateValue);
		
		JLabel nSXLabel = new JLabel("Nhà sản xuất :");
		nSXLabel.setBounds(89, 116, 118, 14);
		panel.add(nSXLabel);
		
		JLabel nSXValue = new JLabel(bike.getManuafaturer());
		nSXValue.setHorizontalAlignment(SwingConstants.RIGHT);
		nSXValue.setBounds(213, 116, 132, 14);
		panel.add(nSXValue);
		
		JLabel costLabel = new JLabel("Giá mua xe (VNĐ) :");
		costLabel.setBounds(89, 140, 118, 14);
		panel.add(costLabel);
		
		JLabel costValue = new JLabel(String.valueOf(bike.getCost()));
		costValue.setHorizontalAlignment(SwingConstants.RIGHT);
		costValue.setBounds(213, 140, 132, 14);
		panel.add(costValue);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBox.background"));
		panel_1.setBounds(0, 217, 434, 44);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			// Dong dialog
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnOk.setBounds(304, 7, 120, 30);
		panel_1.add(btnOk);
		
		JLabel lblNewLabel = new JLabel("Bảng xem thông tin chi tiết xe");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 434, 30);
		contentPanel.add(lblNewLabel);
	}
}
