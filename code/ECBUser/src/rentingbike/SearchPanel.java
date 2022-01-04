package rentingbike;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.ecb.bean.Bike;

public class SearchPanel  extends JPanel{
	
	private JTextField inputCodeField;//ma xe
	private JButton codeButton;//nut nhan de tim kiem xe
	private RentingBikeController controller;
	private JLabel notificationLabel;
	public SearchPanel() {
		buildControl();
	}
	
	public void buildControl() {
		this.setBounds(5,130,750,50);
//		upcontentPanel.setBackground(Color.BLACK);
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		JLabel inputCodeLabel = new JLabel("Nhập mã xe");
		this.add(inputCodeLabel);
		inputCodeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		inputCodeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		inputCodeField = new JTextField();
		inputCodeField.setPreferredSize( new Dimension( 200, 30 ) );
		this.add(inputCodeField);
		
		codeButton = new JButton("Tìm kiếm xe");
		codeButton.setPreferredSize( new Dimension( 150, 30 ) );
		this.add(codeButton);
		
		codeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bikeCode = inputCodeField.getText();
				Bike bike = controller.getBikeByCode(bikeCode);
				if (bike!=null) {
					controller.updateDataPanel(bike);
					showNotification("                              ");	
				}else {
					showNotification("Xe không tồn tại hoặc đã bị thuê");
				}
			}
		});
		notificationLabel = new JLabel("                              ");
		this.add(notificationLabel);
	}
	public void showNotification(String content){
		notificationLabel.setText(content);
}
	public void setController(RentingBikeController controller) {
		this.controller = controller;
	}
}
