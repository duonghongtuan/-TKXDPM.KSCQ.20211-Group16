package returnbike.test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.ecb.bean.Order;

import returnbike.ReturnBikeController;

@RunWith(Parameterized.class)
public class ChooseBillTest {

	String customerId;
	ArrayList<Order> orders;
	Object expectedResult;

	public ChooseBillTest(String customerId, ArrayList<Order> orders, Object expectedResult) {
		this.customerId = customerId;
		this.orders = orders;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testChooseBill() {
		ReturnBikeController returnBikeController = new ReturnBikeController();
		HashMap<String, Order> listOrder = returnBikeController.getOrdersNotPayment(customerId, orders);
		assertEquals("Error in get list bill for customer", this.expectedResult, Integer.valueOf(listOrder.size()));
	}
}
