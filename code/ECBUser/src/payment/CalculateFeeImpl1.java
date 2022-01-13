package payment;

public class CalculateFeeImpl1 implements CalculateFee {
	public double getTotalCost(int bikeType, long minutes) {
		double total = 0;

		// count total price
		if (minutes <= 10) {
			total = 0;
		} else if (minutes <= 30) {
				total = 10000;
		} else {
			if (bikeType != 0 ) {
				total = 10000 + (Math.ceil((minutes - 30) * 1.0 / 15) * 3000) * 1.5;
			}
			else {
				total = 10000 + Math.ceil((minutes - 30) * 1.0 / 15) * 3000;
			}
		}

		return total;
	}
}
