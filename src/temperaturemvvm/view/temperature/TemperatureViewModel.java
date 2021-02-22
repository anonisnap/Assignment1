package temperaturemvvm.view.temperature;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperaturemvvm.mediator.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener {

	private TemperatureModel temperatureModel;
	private DoubleProperty temperature;
	private StringProperty temperatureAsString;
	private StringProperty id;

	public TemperatureViewModel(TemperatureModel temperatureModel) {
		this.temperatureModel = temperatureModel;
		temperature = new SimpleDoubleProperty();
		temperatureAsString = new SimpleStringProperty();
		id = new SimpleStringProperty();
		temperatureModel.addListener("Temperature", this);
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


	public StringProperty temperatureProperty() {
		return temperatureAsString;
	}

	public StringProperty idProperty() {
		return id;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Platform.runLater(this::updateTemp);
	}

}
