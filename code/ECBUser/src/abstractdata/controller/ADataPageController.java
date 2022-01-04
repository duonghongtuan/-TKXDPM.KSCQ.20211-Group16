package abstractdata.controller;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import abstractdata.gui.ADataListPane;
import abstractdata.gui.ADataPagePane;
import abstractdata.gui.ADataSearchPane;
import abstractdata.gui.ADataSinglePane;

public abstract class ADataPageController<T> {
	private ADataPagePane<T> pagePane;

	public ADataPageController() {
		createGUI();
	}
	
	public ADataPageController(String nouse) {
		
	}
	
	
	

	public void initData() {
		// use for child class initialize data
	}

	public void createGUI() {
		ADataSearchPane searchPane = createSearchPane();

		ADataListPane<T> listPane = createListPane();

		searchPane.setController(new IDataSearchController() {
			public void search(Map<String, String> searchParams) {
				List<? extends T> list = ADataPageController.this.search(searchParams);
				listPane.updateData(list);
			}
		});
		searchPane.fireSearchEvent();
	
		pagePane = new ADataPagePane<T>(searchPane, listPane);
	}

	public ADataPagePane<T> getDataPagePane() {
		return pagePane;
	}

	public abstract ADataSearchPane createSearchPane();

	public abstract List<? extends T> search(Map<String, String> searchParams);

	public abstract ADataSinglePane<T> createSinglePane();

	public abstract ADataListPane<T> createListPane();
}
