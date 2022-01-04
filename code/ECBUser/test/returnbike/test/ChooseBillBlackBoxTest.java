package returnbike.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
import com.ecb.bean.Order;

public class ChooseBillBlackBoxTest extends ChooseBillTest {
	public ChooseBillBlackBoxTest(String customerId, ArrayList<Order> orders, Object expectedResult) {
		super(customerId, orders, expectedResult);
		// TODO Auto-generated constructor stub
	}

	@Parameterized.Parameters
	public static Collection<Object[]> allTestCase() {
		// Truong hop khong co hoa don nao trong csdl
		ArrayList<Order> orderNull = null;
		
		// Truong hop khong co hoa don nao trong csdl
		ArrayList<Order> orderSize0 = new ArrayList<Order>();
		
		// Truong hop co hoa don
		ArrayList<Order> orderCustomer = new ArrayList<Order>();
		orderCustomer.add(new Order("order1", "customer1", "16262659", 2, "station2", "station1", 1845,
				null, null, true, "bike1"));
		orderCustomer.add(new Order("order2", "customer1", "99999999", 2, "station2", "station1", 1845,
				null, null, true, "bike2"));
		orderCustomer.add(new Order("order3", "customer2", "87654321", 2, "station2", "station1", 1845,
				null, null, true, "bike3"));
		orderCustomer.add(new Order("order4", "customer2", "12345678", 2, "station2", "station1", 1845,
				null, null, false, "bike4"));
		
		return Arrays.asList(new Object[][] { 
			{ "customer1", orderNull, 0 }, // danh sach tat ca hoa don bi null
			{ "customer1", orderSize0, 0 }, // khong co hoa don nao trong danh sach
			{ "customer1", orderCustomer, 0 }, // KH khong da thanh toan tat ca hoa don
			{ "customer2", orderCustomer, 1 }, // KH vua co hoa don thanh toan va chua thanh toan
			{ "customer3", orderCustomer, 0 }, // KH khong co hoa don nao
		});
	}
}
