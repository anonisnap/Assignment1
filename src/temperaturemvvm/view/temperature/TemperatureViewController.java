package temperaturemvvm.view.temperature;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Window;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.ViewModel;

public class TemperatureViewController implements ViewController {
	@FXML private Label radiatorPowerLabel;
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

	public void init(ViewHandler viewHandler, ViewModel temperatureViewModel, Region root) {
		this.viewHandler = viewHandler;
		this.temperatureViewModel = (TemperatureViewModel) temperatureViewModel;
		this.root = root;
		filterLabel.textProperty().bind(this.temperatureViewModel.idProperty());
		outputLabel.textProperty().bind(this.temperatureViewModel.temperatureProperty());
		radiatorPowerLabel.textProperty().bind(this.temperatureViewModel.radiatorPower());
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

	public void turnPowerUp() {
		temperatureViewModel.turnUpRadiator();
	}

	public void turnPowerDown() {
		temperatureViewModel.turnDownRadiator();
	}

	@Override
	public void swapScene(ActionEvent actionEvent) {
		viewHandler.openView(((RadioMenuItem) actionEvent.getSource()).getText().toLowerCase());
	}

	@Override
	public void close() {
		viewHandler.close();
	}
}
