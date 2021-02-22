package temperaturemvvm.view.temperature;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import temperaturemvvm.core.ViewHandler;

public class TemperatureViewController {
	@FXML private Label outputLabel;
	@FXML private TextField filterField;
	@FXML private Label filterLabel;
	private ViewHandler viewHandler; // for optional GUI change capability
	private TemperatureViewModel temperatureViewModel;
	private Region root;

	public TemperatureViewController() {
		temperatureViewModel = null;
		root = null;
	}

	public void init(ViewHandler viewHandler, TemperatureViewModel temperatureViewModel, Region root) {
		this.viewHandler = viewHandler;
		this.temperatureViewModel = temperatureViewModel;
		this.root = root;
		filterLabel.textProperty().bind(temperatureViewModel.idProperty());
		outputLabel.textProperty().bind(temperatureViewModel.temperatureProperty());
	}

	public Region getRoot() {
		return root;
	}

	@FXML
	private void updateButtonPressed() {
		temperatureViewModel.updateTemp();
		filterField.setText("");
	}

	@FXML
	private void onFilter() {
		temperatureViewModel.setId(filterField.getText());
		updateButtonPressed();
	}
}
