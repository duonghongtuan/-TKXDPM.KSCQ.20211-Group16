package rentingbike;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.ecb.bean.Bike;

public class DataPanel extends JPanel{
	private Bike bike;
	private JLabel nameField;//ten xe
	private JLabel typeField;//loai xe
	private JLabel weightField;//trong luong
	private JLabel licenseField;// bien so xe
	private JLabel dateField;//ngay san xuat
	private JLabel costField;//gia thanh
	private JButton rentingButton;//an nut de thue xe
	private JLabel notificationLabel;//hien thi thong bao
	InputCreditCardView notification;
	RentingBikeController controller;
	
	public DataPanel() {
		BuildControl();
	}
	private void BuildControl() {
		
		this.setLayout(null);
		this.setBounds(5,190,750,250);
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		
		JLabel nameLabel = new JLabel("Tên xe");
		nameField = new JLabel();
		this.add(nameLabel);
		this.add(nameField);
		nameLabel.setBounds(200,6,100,40);
		nameField.setBounds(300,11,200,30);
		
		
		JLabel typeLabel = new JLabel("Loại xe");
		typeField = new JLabel();
		this.add(typeLabel);
		this.add(typeField);
		typeLabel.setBounds(200,50,100,40);
		typeField.setBounds(300,56,200,30);
		

		
		JLabel weightLabel = new JLabel("Trọng lượng");
		weightField = new JLabel();
		this.add(weightLabel);
		this.add(weightField);
		weightLabel.setBounds(200,85,100,40);
		weightField.setBounds(300,90,200,30);
		

		
		JLabel licenseLabel = new JLabel("Biển số xe");
		licenseField = new JLabel();
		this.add(licenseLabel);
		this.add(licenseField);
		licenseLabel.setBounds(200,120,100,40);
		licenseField.setBounds(300,125,200,30);
		
		
		JLabel dateLabel = new JLabel("Ngày sản xuất");
		dateField = new JLabel();
		this.add(dateLabel);
		this.add(dateField);
		dateLabel.setBounds(200,160,100,40);
		dateField.setBounds(300,166,200,30);
		
		JLabel costLabel = new JLabel("Tiền đặt cọc");
		costField = new JLabel();
		this.add(costLabel);
		this.add(costField);
		costLabel.setBounds(200,201,100,40);
		costField.setBounds(300,207,200,30);
		rentingButton = new JButton("Thuê xe");
		rentingButton.setBounds(505,205,150,30);
		this.add(rentingButton);	
		
		notificationLabel = new JLabel("");
		notificationLabel.setBounds(500,225,200,30);
		this.add(notificationLabel);
		Font f = notificationLabel.getFont();
		notificationLabel.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		rentingButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCaution();
			}
		});
	}
public void showCaution() {
		
		if (bike!=null) {
			notification = new InputCreditCardView(this.bike);
			notification.setController(controller);
			notificationLabel.setText("");
		}else {
			notificationLabel.setText("Chưa có bất kì xe nào được chọn!");
		}
	}
	
	
	public void updateBikeInfor() {
		nameField.setText(bike.getName());
		int type = bike.getType();
        if (type == 0) {
        	typeField.setText( "Xe đạp đơn");
        } else if (type == 1) {
        	typeField.setText("Xe đạp điện");
        } else if (type == 2) {
        	typeField.setText("Xe đạp đôi");
        }else {
        	typeField.setText("Xe đạp đôi điện");
        }
		
		weightField.setText(Double.toString(bike.getWeight()));
		licenseField.setText(bike.getLisencePlate());
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		dateField.setText(dateFormat.format(bike.getManuafaturingDate()));
		costField.setText(Double.toString(bike.getCost()*0.4));
	}
	public void setData(Bike bike) {
		this.bike = bike;
		updateBikeInfor();
	}
	public void setController(RentingBikeController controller) {
		this.controller = controller;
	}
	
}
