package temperaturemvvm.view.temperature;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.ViewModel;

public class TemperatureViewController implements ViewController {
	@FXML private TextField upper, lower;
	@FXML private VBox trackBox;
	@FXML private Label warningLabel;
	@FXML private TextField filterField;
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
	}

	public Region getRoot() {
		return root;
	}

	@FXML
	private void update() {
		temperatureViewModel.updateTemp();
		filterField.setText("");
	}

	@FXML
	private void addToList() {
		temperatureViewModel.setId(filterField.getText());
		HBox entry = createHBox();
		trackBox.getChildren().add(entry);
		update();
	}

	private HBox createHBox() {
		HBox box = new HBox();
		Label infoLabel = new Label();
		infoLabel.textProperty().bind(temperatureViewModel.temperatureProperty());
		box.getChildren().add(infoLabel);
		style(box);
		return box;
	}

	private void style(HBox box) {
		box.autosize();
		box.setStyle("-fx-background-color: #9bf");
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
