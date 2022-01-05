package rentingbike;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ecb.bean.Bike;
import com.ecb.bean.CreditCard;
import com.ecb.bean.Customer;
import com.ecb.bean.DockingStation;
import com.ecb.bean.Order;
import com.sun.xml.bind.v2.runtime.output.FastInfosetStreamWriterOutput;

import api.EcoBikeApi;
import main.ECBUser;

public class InputCreditCardView extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private JTextField orderID;
	private RentingBikeController controller;
	private Bike bike;
	private Order order;
	private JLabel alertLabel;
	public InputCreditCardView() {
		// TODO Auto-generated constructor stub
		buildControls();
	}


	public InputCreditCardView(Bike bike) {
		// TODO Auto-generated constructor stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.bike = bike;
		buildControls();
	}
	
	public void buildControls() {
		setResizable(false);
		setTitle("Nhap ma xe");
		setBounds(100, 100, 275, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel notification = new JPanel();
		notification.setBorder(new LineBorder(Color.LIGHT_GRAY));
		notification.setBounds(10, 10, 240, 115);
		contentPanel.add(notification);
		notification.setLayout(null);
		
		
	JLabel label = new JLabel("Vui lòng nhập mã thẻ tín dụng");
	notification.add(label);
	label.setBounds(25,5,260,15);
	
	orderID = new JTextField();
	orderID.setBounds(25,30,180,30);
	notification.add(orderID);
	
	alertLabel = new JLabel("");
	alertLabel.setBounds(25,65,260,15);
	notification.add(alertLabel);
	
	
	JButton button = new JButton("Xong");
	button.setBounds(105,80,105,30);
	notification.add(button);
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cardId =orderID.getText().trim();
			if (!cardId.equals("")&&!cardId.equals(null)) {
				CreditCard card = getCard(cardId);
				if (card!=null) {
					double deposit = bike.getCost()*0.4;
					if (checkCard(cardId)) {
					if (card.getAmount()>=deposit) {
						addOrder(cardId);
						controller.addOrder(order);
						double resCard = controller.minusAmountCard(cardId, deposit);
						if (resCard!=-1) {
							DockingStation station = controller.getDockingStation(bike.getStationId());
							station.setFreeSpace(station.getFreeSpace()-1);
							controller.updateDockingStation(station);
							Bike updateBike = bike;
							updateBike.setRent(true);
							controller.updateBike(updateBike);
							showSuccess();
							if(controller.finishedEventCallback != null) {
								controller.finishedEventCallback.run();
							}
						}
						else {
							alertLabel.setText("Thao tác với thẻ không thành công!");
						}
					}
					else {
						alertLabel.setText("Số tiền trong thẻ không đủ!");
					}
					} else {
						alertLabel.setText("Thẻ đã được sử dụng!");
					}
				}else {
					alertLabel.setText("Thẻ không tồn tại trên hệ thống!");
				}
			}
			else {
				alertLabel.setText("Vui lòng điền mã thẻ");
			}
		}
	});
	
	}
	private void showSuccess() {
		alertLabel.setText("Thuê xe thành công");
	}
	public void setController(RentingBikeController controller) {
		this.controller = controller;
	}
	public void addOrder(String cardId) {
		UUID orderId = UUID.randomUUID();
		String customerId = ECBUser.customer.getCustomerId();
		order = new Order(orderId.toString(),customerId,cardId,bike.getCost(),this.bike.getStationId(),"dont know",200,new Date(),new Date(),false,this.bike.getBikeId());
	}
	public CreditCard getCard(String cardId) {
		return controller.getCard(cardId);
	}
	public boolean checkCard (String cardId) {
		return controller.checkCard(cardId);
	}
}
