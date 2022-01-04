package other;

import java.awt.Font;

public class StringStyle {
	public static int BIG_TEXT = 20;
	public static int NORMAL_TEXT = 15;
	public static int SMALL_TEXT = 10;
	
	public static Font NORMAL_FONT2 = new Font("Time New Roman", Font.PLAIN, NORMAL_TEXT);
	public static Font NORMAL_FONT = new Font("Time New Roman", Font.PLAIN, NORMAL_TEXT);
	public static Font ICON_FONT = new Font("Time New Roman", Font.BOLD, 45);
	public static Font BIG_FONT = new Font("Time New Roman", Font.BOLD, BIG_TEXT);
	
	public static String buildText(Object name, Object value, String color) {
		return String.format("<html><p style=\"width:210px\" >"
					+ "<span style=\" font-size:11px\"  >"+name+": </span>"
					+ "<span style=\"font-size: 11px; font-style: bold; color:%s\"  >"+ value + "</span>"
					+"</p></html>"
			, color);
	}
}
