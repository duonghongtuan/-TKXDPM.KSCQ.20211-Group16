package other;

public class Constants {
	public static String ALL = "Tất Cả";
	public static class BIKE{
		public static int bike = 0;
		public static int ebike = 1;
		public static int twinBike = 2;
		
		public static String bikeType[] = {"Xe đạp đơn thường (Bike)", "Xe đạp đơn điện (EBike)", "Xe đạp đôi thường (TWinBike)", ALL};
		
		public static String getBikeType(int typeBike) {
			if(bikeType.length-1>typeBike)
				return bikeType[typeBike];
			else
				return "noResult";
		}
		public static int getBikeTypeId(String type) {
			for (int i = 0; i < bikeType.length; i++) {
				if(bikeType[i].equals(type))
					return i;
			}
			return -1;
		}
		public static void main(String[] args) {
			System.out.println(getBikeTypeId(BIKE.bikeType[1]));
		}
	}
}
