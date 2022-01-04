package payment.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import payment.PaymentController;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TotalCostWhiteBoxTest {
	private long minutes;
	private int bikeType;

	private double expectedCost;

	private PaymentController paymentController = new PaymentController();

	public TotalCostWhiteBoxTest(long minutes, int bikeType, double expectedCost) {
		super();
		this.minutes = minutes;
		this.bikeType = bikeType;
		this.expectedCost = expectedCost;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] {
				// <= 10 minutes
				{ 8, 0, 0 }, // { minutes, bikeType, expectedCost }
				// <= 40 minutes
				{ 35, 0, 10000 },
				// > 40 minutes, bike 0
				{ 60, 0, 16000 },
				// > 40 minutes, bike != 0
				{ 60, 1, 19000 } });
	}

	@Test
	public void getTotalCost() {
		assertTrue("Unexpected cost!: " + paymentController.getTotalCost(bikeType, minutes),
				paymentController.getTotalCost(bikeType, minutes) == expectedCost);
	}

}