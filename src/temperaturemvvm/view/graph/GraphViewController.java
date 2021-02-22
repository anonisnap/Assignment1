package temperaturemvvm.view.graph;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.Region;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.temperature.TemperatureViewModel;

public class GraphViewController implements ViewController {
	private ViewHandler viewHandler;
	private TemperatureViewModel temperatureViewModel;
	private Region root;

	public void init(ViewHandler viewHandler, TemperatureViewModel temperatureViewModel, Region root) {
		this.viewHandler = viewHandler;
		this.temperatureViewModel = temperatureViewModel;
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
}
