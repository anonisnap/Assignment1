package temperaturemvvm.mediator;

import temperaturemvvm.radiator.Radiator;

import java.beans.PropertyChangeListener;

public class RadiatorModelManager implements RadiatorModel {
	private Radiator radiator;

	public RadiatorModelManager(){
		radiator = new Radiator();
	}

	public Radiator getRadiator() {
		return radiator;
	}

	@Override
	public void turnUp() {
		radiator.turnUp();
	}

	@Override
	public void turnDown() {
		radiator.turnDown();
	}

	@Override
	public int getPower() {
		return radiator.getPower();
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {
		radiator.addListener(propertyName, listener);
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		radiator.removeListener(propertyName, listener);
	}
}
