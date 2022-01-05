package other;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SourcePath {

	public static ImageIcon getBikeImg() {
		BufferedImage bufferedImage=null;
		try {
			bufferedImage = ImageIO.read(SourcePath.class.getResource("../img/bike.png"));
//			Image dimg = bufferedImage.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),
//			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			return imageIcon;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ImageIcon getStationImg() {
		BufferedImage bufferedImage=null;
		try {
//			imgLabel.setBounds(10, 11, 106, 66);
			
			bufferedImage = ImageIO.read(SourcePath.class.getResource("../img/station.png"));
			Image dimg = bufferedImage.getScaledInstance(bufferedImage.getWidth()/3, bufferedImage.getHeight()/3,
			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			return imageIcon;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ImageIcon getIcon(String name) {
		BufferedImage bufferedImage=null;
		try {
			bufferedImage = ImageIO.read(SourcePath.class.getResource("../img/"+name+".png"));
			Image dimg = bufferedImage.getScaledInstance(50, 50,
			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			return imageIcon;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getIcon("user"));
		System.out.println(getIcon("bike"));
	}

}
