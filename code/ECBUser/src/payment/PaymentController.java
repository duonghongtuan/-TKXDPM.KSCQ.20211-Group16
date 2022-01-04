package payment;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import com.ecb.bean.Bike;
import com.ecb.bean.CreditCard;
import com.ecb.bean.DockingStation;
import com.ecb.bean.Order;

import api.EcoBikeApi;
import api.EcoBikeApiFactory;

public class PaymentController {

	private PaymentDialog paymentDialog;
	private Order order;
	private OrderInfoPanel orderInfoPanel;
	private PaymentInfoPanel paymentInfoPanel;
	private EcoBikeApi api;
	private DecimalFormat decimalFormat;

	private DockingStation startStation;
	private DockingStation endStation;
	private Bike bike;

	Runnable finishedEventCallback;

	public PaymentController() {
		api = EcoBikeApiFactory.getInstance();
		orderInfoPanel = new OrderInfoPanel();
		orderInfoPanel.setPaymentController(this);

		paymentInfoPanel = new PaymentInfoPanel();
		paymentInfoPanel.setPaymentController(this);

		decimalFormat = new DecimalFormat("#.##");
		decimalFormat.setGroupingUsed(true);
		decimalFormat.setGroupingSize(3);
	}

	public void showPaymentDialog() {
		if (order.isPayment()) {
			openCheckoutDialog("Đơn đã được thanh toán!");
			return;
		}
		startStation = api.getDockingStation(order.getStartStationId());
		endStation = api.getDockingStation(order.getFinishStationId());
		bike = api.getBikeByCode(order.getBikeId());

		paymentDialog = new PaymentDialog(orderInfoPanel, paymentInfoPanel);
		paymentDialog.setLocationRelativeTo(SwingUtilities.windowForComponent(paymentDialog));
		paymentDialog.update(bike, startStation, endStation);
		paymentDialog.setVisible(true);

	}

	public void openCheckoutDialog(String message) {
		AlertCheckoutDialog alertCheckoutDialog = new AlertCheckoutDialog(message);
		alertCheckoutDialog.setLocationRelativeTo(SwingUtilities.windowForComponent(alertCheckoutDialog));
		alertCheckoutDialog.setVisible(true);
	}

	public void closePaymentDialog() {
		paymentDialog.setVisible(false);
		paymentDialog.dispose();
	}
	
	public long getTimeRent() {
		long millis = Math.abs(order.getFinishTime().getTime()
				- order.getStartTime().getTime());
		return millis;
	}
	
	public double getTotalCost(int bikeType, long minutes) {
		double total = 0;

		// count total price
		if (minutes <= 10) {
			total = 0;
		} else if (minutes <= 40) {
			total = 10000;
		} else {
			if (bikeType != 0) {
				total = 10000 + (Math.ceil((minutes - 40) * 1.0 / 15) * 3000) * 1.5;
			} else {
				total = 10000 + Math.ceil((minutes - 40) * 1.0 / 15) * 3000;
			}
		}

		return total;
	}

	public void checkOut(String cardId, double total) {
		if ("".equals(cardId)) {
			openCheckoutDialog("Vui lòng nhập mã thẻ!");
			return;
		}
		CreditCard card = api.getCard(cardId);
		if (card == null) {
			openCheckoutDialog("Mã thẻ không tồn tại!");
			return;
		}
		if (!card.getCardId().equals(order.getCardId())) {
			openCheckoutDialog("Mã thẻ không chính xác!");
			return;
		}
		// check card amount
		if (card.getAmount() < total) {
			openCheckoutDialog(
					String.format("<html>Tài khoản không đủ tiền! <br/><br/>Mã thẻ: %s <br/>Số tiền: %s</html>", cardId,
							decimalFormat.format(card.getAmount())));
			return;
		}

		// minus amount
		api.plusAmount(card.getCardId(), bike.getCost());
		double res = api.minusAmount(card.getCardId(), total);

		// update order
		order.setPayment(true);
		order.setAmount(getTotalCost(bike.getType(), TimeUnit.MILLISECONDS.toMinutes(getTimeRent())));
		order.setTimeRent(getTimeRent());
		api.updateOrder(order);

		// update bike status
		bike.setRent(false);
		bike.setStationId(endStation.getStationId());
		api.updateBike(bike);

		// update station
		int free = endStation.getFreeSpace();
		free--;
		endStation.setFreeSpace(free);
		api.updateDockingStation(endStation);

		// show result
		closePaymentDialog();
		openCheckoutDialog(String.format(
				"<html>Thanh toán thành công! <br/><br/>Mã thẻ: %s <br/>Tài khoản: %s<br/>Đã thanh toán: %s<br/>Còn lại: %s</html>",
				cardId, decimalFormat.format(card.getAmount() + bike.getCost()), decimalFormat.format(total),
				decimalFormat.format(res)));
		if (finishedEventCallback != null) {
			finishedEventCallback.run();
		}
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public void setFinishedEventCallback(Runnable finishedEventCallback) {
		this.finishedEventCallback = finishedEventCallback;
	}

}
