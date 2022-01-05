package viewstation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.ecb.bean.DockingStation;

import abstractdata.gui.ADataSinglePane;
import other.SourcePath;
import other.StringStyle;

public class StationSinglePane extends ADataSinglePane<DockingStation> {
	public StationSinglePane() {
	}

	private JLabel labelStationId;
	private JLabel labelId;
	private JLabel labelName;
	private JLabel labelAddress;
	private JLabel labelFreeSpace;
	private JLabel lableFreeBike;
	private JLabel labelPhone;

	@Override
	public void buildControls() {
		super.buildControls();

		JLabel label = new JLabel();
		label.setIcon(SourcePath.getStationImg());
		imgPanel.add(label);

		labelId = new JLabel();
		labelName = new JLabel();
		labelAddress = new JLabel();
		labelFreeSpace = new JLabel();
		lableFreeBike = new JLabel();
		labelPhone = new JLabel();

		inforPanel.setLayout(new BoxLayout(inforPanel, BoxLayout.Y_AXIS));
		//inforPanel.setBorder(new LineBorder(Color.black, 2));
		inforPanel.add(labelId);
		inforPanel.add(labelName);
		inforPanel.add(labelAddress);
		inforPanel.add(labelFreeSpace);
		inforPanel.add(lableFreeBike);
		inforPanel.add(labelPhone);

	}

	@Override
	public void displayData() {
		DockingStation station = getData();
		labelId.setText(buildText("Mã", station.getStationId()));
		labelName.setText(buildText("Trạm", station.getName()));
		labelAddress.setText(buildText("Địa chỉ", station.getAddress()));
		labelFreeSpace.setText(buildText("Số chỗ trống", station.getFreeSpace()));
		lableFreeBike.setText(buildText("Số xe trong bãi", station.getFreeBike()));
		labelPhone.setText(buildText("SĐT", station.getPhone()));
	}

}
