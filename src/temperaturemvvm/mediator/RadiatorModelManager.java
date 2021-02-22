package temperaturemvvm.mediator;

import temperaturemvvm.radiator.Radiator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RadiatorModelManager implements RadiatorModel {
	private Radiator radiator;
	private PropertyChangeSupport property;

	public RadiatorModelManager(){
		radiator = new Radiator();
		property = new PropertyChangeSupport(this);
	}

	public Radiator getRadiator() {
		return radiator;
	}

	@Override
	public void turnUp() {
		radiator.turnUp();
		fireChange();
	}

	@Override
	public void turnDown() {
		radiator.turnDown();
		fireChange();
	}

	private void fireChange() {
		property.firePropertyChange("Power Changed", null, radiator.getPower());
	}

	@Override
	public int getPower() {
		return radiator.getPower();
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {
		if (propertyName == null) {
			property.addPropertyChangeListener(listener);
		} else {
			property.addPropertyChangeListener(propertyName, listener);
		}
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		property.removePropertyChangeListener(propertyName, listener);
	}
}
