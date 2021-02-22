package temperaturemvvm.view.temperature;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperaturemvvm.mediator.RadiatorModel;
import temperaturemvvm.mediator.TemperatureModel;
import temperaturemvvm.view.ViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener, ViewModel {
	private TemperatureModel temperatureModel;
	private DoubleProperty temperature;
	private StringProperty temperatureAsString;
	private StringProperty id;

	private RadiatorModel radiatorModel;
	private StringProperty radiatorPower;

	public TemperatureViewModel(TemperatureModel temperatureModel, RadiatorModel radiatorModel) {
		this.temperatureModel = temperatureModel;
		this.radiatorModel = radiatorModel;
		temperature = new SimpleDoubleProperty();
		temperatureAsString = new SimpleStringProperty();
		id = new SimpleStringProperty();
		radiatorPower = new SimpleStringProperty();
		temperatureModel.addListener("Temperature", this);
		radiatorModel.addListener("Power Changed", this);
	}

	public void updateTemp() {
		getLastTemp();
		temperatureAsString.setValue(id.getValue() + " : " + temperature.getValue());
	}

	private void getLastTemp() {
		temperature.setValue(temperatureModel.getLastInsertedTemperature(id.getValue()).getValue());
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public void turnUpRadiator() {
		radiatorModel.turnUp();
		radiatorPower.setValue("Radiator Power Level: " + radiatorModel.getPower());
	}

	public void turnDownRadiator() {
		radiatorModel.turnDown();
		radiatorPower.setValue("Radiator Power Level: " + radiatorModel.getPower());
	}

	public StringProperty temperatureProperty() {
		return temperatureAsString;
	}

	public StringProperty idProperty() {
		return id;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Platform.runLater(this::updateTemp);
		Platform.runLater(this::updatePower);
	}

	private void updatePower() {
		radiatorPower.setValue("Radiator Power Level: " + radiatorModel.getPower());
	}

	public StringProperty radiatorPower() {
		return radiatorPower;
	}
}
