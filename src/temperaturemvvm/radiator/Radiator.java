package temperaturemvvm.radiator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Radiator {
	private RadiatorState currentState;
	private PropertyChangeSupport property;

	public Radiator() {
		this.currentState = new OffState();
		this.property = new PropertyChangeSupport(this);
	}

	public void turnUp() {
		currentState.turnUp(this);
	}

	public void turnDown() {
		currentState.turnDown(this);
	}

	public int getPower() {
		return currentState.getPower();
	}

	void setPowerState(RadiatorState state) {
		property.firePropertyChange("Changed Power", null, currentState.getPower());
		currentState = state;
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
