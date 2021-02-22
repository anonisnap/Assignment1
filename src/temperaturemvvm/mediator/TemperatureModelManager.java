package temperaturemvvm.mediator;

import temperaturemvvm.model.Temperature;
import temperaturemvvm.model.TemperatureList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel {
	private TemperatureList temperatureList;
	private PropertyChangeSupport property;

	public TemperatureModelManager() {
		property = new PropertyChangeSupport(this);
		temperatureList = new TemperatureList();
	}

	@Override
	public void addTemperature(String id, double value) {
		Temperature temperature = new Temperature(id, value);
		Temperature old = getLastInsertedTemperature();
		this.temperatureList.addTemperature(temperature);
		if (old != null && old.getValue() != temperature.getValue()) {
			property.firePropertyChange("Temperature", old, temperature);
			// System.out.println("-->" + temperature + " (from: " + old + ")");
		}
	}

	@Override
	public Temperature getLastInsertedTemperature() {
		return temperatureList.getLastTemperature(null);
	}

	@Override
	public Temperature getLastInsertedTemperature(String id) {
		return temperatureList.getLastTemperature(id);
	}

	public void addListener(String propertyName, PropertyChangeListener listener) {
		if (propertyName == null) {
			property.addPropertyChangeListener(listener);
		} else {
			property.addPropertyChangeListener(propertyName, listener);
		}
	}

	public void removeListener(String propertyName, PropertyChangeListener listener) {
		property.removePropertyChangeListener(propertyName, listener);
	}
}
