package temperaturemvvm.view.temperature;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.ViewModel;

public class TemperatureViewController implements ViewController {
	@FXML private Label t1Label;
	@FXML private Label t2Label;
	@FXML private TextField upper, lower;
	@FXML private Label warningLabel;
	@FXML private Label radiatorPowerLabel;
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
		radiatorPowerLabel.textProperty().bind(this.temperatureViewModel.radiatorPower());
		warningLabel.textProperty().bind(this.temperatureViewModel.warningProperty());
		upper.textProperty().bindBidirectional(this.temperatureViewModel.upperLimitProperty());
		lower.textProperty().bindBidirectional(this.temperatureViewModel.lowerLimitProperty());
		t1Label.textProperty().bind(this.temperatureViewModel.temperature1Property());
		t2Label.textProperty().bind(this.temperatureViewModel.temperature2Property());
	}

	public Region getRoot() {
		return root;
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
