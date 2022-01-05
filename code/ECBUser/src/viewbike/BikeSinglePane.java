package viewbike;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ecb.bean.Bike;

import abstractdata.gui.ADataSinglePane;
import other.Constants;
import other.SourcePath;
import other.StringStyle;

@SuppressWarnings("serial")
public class BikeSinglePane extends ADataSinglePane<Bike>{
	
	private JLabel labelName;
	private JLabel labelType;
	private JLabel labelWeight;
	private JLabel labelLicensePlate;
	private JLabel labelManufaturer;
	private JLabel labelManufaturingDate;
	private JLabel labelCost;
	private JLabel labelStatus;
	
	
	
	public BikeSinglePane() {
		// TODO Auto-generated constructor stub
	
	}

	
	public BikeSinglePane(Bike t) {
		super(t);
	}
	
	@Override
	public void buildControls() {
		// TODO Auto-generated method stub
		super.buildControls();
//		setBorder(new LineBorder(Color.black, 2));
		
		JLabel label = new JLabel();
		label.setIcon(SourcePath.getBikeImg());
		imgPanel.add(label);

		labelName = new JLabel();
		labelType = new JLabel();
		labelWeight = new JLabel();
		labelLicensePlate = new JLabel();
		labelManufaturer = new JLabel();
		labelManufaturingDate = new JLabel();
		labelStatus = new JLabel();
		labelCost = new JLabel();
	
		inforPanel.setLayout(new BoxLayout(inforPanel, BoxLayout.Y_AXIS));
//		inforPanel.setBorder(new LineBorder(Color.black, 2));
		inforPanel.add(labelName);
		inforPanel.add(labelType);
		inforPanel.add(labelWeight);
		inforPanel.add(labelLicensePlate);
		inforPanel.add(labelCost);
		inforPanel.add(labelManufaturer);
		inforPanel.add(labelManufaturingDate);
		inforPanel.add(labelStatus);
	}
	
	
	@Override
	public void displayData() {
		Bike bike = getData();
		labelName.setText(buildText("Mã",bike.getBikeId()));
//		labelType.setText(buildText("TÃªn", bike.getName()));
		labelType.setText(buildText("Loại", Constants.BIKE.getBikeType(bike.getType())));
		labelWeight.setText(buildText("Trọng lượng", bike.getWeight()+" kg"));
		labelLicensePlate.setText(buildText("Biển số xe", bike.getLisencePlate()));
		labelManufaturer.setText(buildText("NSX", bike.getManuafaturer()));
		Date date = bike.getManuafaturingDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		labelManufaturingDate.setText(buildText("Ngày SX", formatter.format(date)));
		labelCost.setText(buildText("Giá", bike.getCost()+ " vnđ"));
		if(bike.isRent()){
			labelStatus.setText(buildText("Trạng thái", "Đã được thuê", "red"));
//			labelStatus.set
		}
		else
			labelStatus.setText(buildText("Trạng thái", "Sẵn sàng", "green"));
	}
	

}
