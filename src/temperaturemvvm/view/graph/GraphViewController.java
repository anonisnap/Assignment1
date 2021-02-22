package temperaturemvvm.view.graph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.Region;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.ViewModel;

public class GraphViewController implements ViewController {
	@FXML private LineChart temperatureGraph;
	private ViewHandler viewHandler;
	private GraphViewModel graphViewModel;
	private Region root;

	public void init(ViewHandler viewHandler, ViewModel graphViewModel, Region root) {
		this.viewHandler = viewHandler;
		this.graphViewModel = (GraphViewModel) graphViewModel;
		this.root = root;
	}

	@Override
	public void swapScene(ActionEvent actionEvent) {
		viewHandler.openView(((RadioMenuItem) actionEvent.getSource()).getText().toLowerCase());
	}

	@Override
	public Region getRoot() {
		return root;
	}

	@Override
	public void close() {
		viewHandler.close();
	}
}
