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
	private DoubleProperty temperature1;
	private DoubleProperty temperature2;
	private StringProperty temperatureAsString1;
	private StringProperty temperatureAsString2;
	private StringProperty id;
	private StringProperty lower, higher;
	private StringProperty warning;
	private RadiatorModel radiatorModel;
	private StringProperty radiatorPower;

	public TemperatureViewModel(TemperatureModel temperatureModel, RadiatorModel radiatorModel) {
		this.temperatureModel = temperatureModel;
		this.radiatorModel = radiatorModel;
		temperature1 = new SimpleDoubleProperty();
		temperature2 = new SimpleDoubleProperty();
		temperatureAsString1 = new SimpleStringProperty();
		temperatureAsString2 = new SimpleStringProperty();
		id = new SimpleStringProperty();
		radiatorPower = new SimpleStringProperty();
		warning = new SimpleStringProperty();
		lower = new SimpleStringProperty();
		higher = new SimpleStringProperty();
		temperatureModel.addListener("Temperature", this);
		radiatorModel.addListener("Power Changed", this);
	}

	public void updateTemp() {
		getLastTemp();
		temperatureAsString1.setValue("Thermometer 1: " + temperature1.getValue());
		temperatureAsString2.setValue("Thermometer 2: " + temperature2.getValue());
		try {
			if (temperature1.getValue() < Double.parseDouble(lower.getValue()) || temperature1.getValue() > Double.parseDouble(higher.get())) {
				warning.setValue("Temperature Warning");
			} else {
				warning.setValue("");
			}
		} catch (NullPointerException nullPointerException) {
			warning.set("");
		}
	}

	private void getLastTemp() {
		temperature1.setValue(temperatureModel.getLastInsertedTemperature("t1").getValue());
		temperature2.setValue(temperatureModel.getLastInsertedTemperature("t2").getValue());
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

	public StringProperty temperature1Property() {
		return temperatureAsString1;
	}

	public StringProperty temperature2Property() {
		return temperatureAsString2;
	}

	public StringProperty idProperty() {
		return id;
	}

	public StringProperty warningProperty() {
		return warning;
	}

	public StringProperty upperLimitProperty() {
		return higher;
	}

	public StringProperty lowerLimitProperty() {
		return lower;
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
