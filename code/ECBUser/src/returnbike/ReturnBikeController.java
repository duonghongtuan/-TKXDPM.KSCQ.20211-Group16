package returnbike;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.Iterator;
import javax.swing.SwingUtilities;
import com.ecb.bean.Bike;
import com.ecb.bean.DockingStation;
import com.ecb.bean.Order;
import api.EcoBikeApi;
import api.EcoBikeApiFactory;
import main.ECBUser;
import payment.PaymentController;

public class ReturnBikeController {

	private ReturnBikeDialog returnBikeDialog;
	private InfoBikeDialog infoBikeDialog;
	private ChooseBillDialog chooseBillDialog;
	private PaymentController paymentController;
	private HashMap<String, Order> listOrder;
	Runnable finishedEventCallback;

	public ReturnBikeController() {
		paymentController = new PaymentController();
		listOrder = new HashMap<String, Order>();
	}

	public void showChooseBillDialog() {
		try {
			initDataChoose();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * Lay danh sach hoa don chua thanh toan cua KH
	 * @param customerId
	 * @param orders
	 * @return
	 */
	@SuppressWarnings("unused")
	public HashMap<String, Order> getOrdersNotPayment(String customerId, ArrayList<Order> orders) {
		if (orders == null) {
			paymentController.openCheckoutDialog("Hiện không có hóa đơn nào!");
		} else {
			Iterator<Order> iter = orders.iterator();
			while (iter.hasNext()) {
				Order order = iter.next();
				if (order.getCustomerId().equals(customerId)) {
					if (order.isPayment() == false) {
						listOrder.put(order.getBikeId(), order);
					}
				}
			}

		}
		return listOrder;
	}

	private void initDataChoose() {
		ArrayList<Order> orders = EcoBikeApiFactory.getInstance().getOrder();
		listOrder = getOrdersNotPayment(ECBUser.customer.getCustomerId(), orders);
		// Check null thi hien thi thong bao
		if (listOrder.isEmpty()) {
			paymentController.openCheckoutDialog("Hiện không có hóa đơn nào!");
			return;
		}

		chooseBillDialog = new ChooseBillDialog(this);
		chooseBillDialog.setLocationRelativeTo(SwingUtilities.windowForComponent(chooseBillDialog));
		chooseBillDialog.setVisible(true);
	}

	@SuppressWarnings("unused")
	void showReturnBikeDialog(Order order) {
		Bike bike = null;
		HashMap<String, String> stations = new HashMap<String, String>();
		DockingStation startStation = null;

		// Lay thoi gian tra xe
		order.setFinishTime(new Date());
		paymentController.setOrder(order);

		// Lay tat ca bai xe
		ArrayList<DockingStation> stationsRes = EcoBikeApiFactory.getInstance().getStation(null);
		for (DockingStation s : stationsRes) {
			stations.put(s.getName(), s.getStationId());
			if (s.getStationId().equals(order.getStartStationId())) {
				startStation = s;
			}
		}
		// Lay xe theo id xe
		bike = EcoBikeApiFactory.getInstance().getBikeByCode(order.getBikeId());

		returnBikeDialog = new ReturnBikeDialog(bike, startStation, stations, order);
		returnBikeDialog.setReturnBikeController(this);
		returnBikeDialog.setLocationRelativeTo(SwingUtilities.windowForComponent(returnBikeDialog));
		returnBikeDialog.setVisible(true);

	}

	public void showInfoBikeDialog() {
		try {
			// Lay xe theo id
			Bike bike = EcoBikeApiFactory.getInstance().getBikeByCode(paymentController.getOrder().getBikeId());
			// Truyen du lieu xe va hien thi dialog
			infoBikeDialog = new InfoBikeDialog(bike);
			infoBikeDialog.setLocationRelativeTo(SwingUtilities.windowForComponent(infoBikeDialog));
			infoBikeDialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public HashMap<String, Order> getListOrder() {
		return listOrder;
	}

	public void setFinishedEventCallback(Runnable finishedEventCallback) {
		this.finishedEventCallback = finishedEventCallback;
		paymentController.setFinishedEventCallback(finishedEventCallback);
	}

	public void onPayment(String finishStationId) {
		paymentController.getOrder().setFinishStationId(finishStationId);
		paymentController.showPaymentDialog();	
	}
}
