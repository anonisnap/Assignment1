package temperaturemvvm.core;

import temperaturemvvm.mediator.RadiatorModel;
import temperaturemvvm.mediator.RadiatorModelManager;
import temperaturemvvm.mediator.TemperatureModel;
import temperaturemvvm.mediator.TemperatureModelManager;

public class ModelFactory {
	private TemperatureModel temperatureModel;
	private RadiatorModel radiatorModel;

	public ModelFactory() {
		temperatureModel = new TemperatureModelManager();
		radiatorModel = new RadiatorModelManager();
	}

	public TemperatureModel getTemperatureModel() {
		return temperatureModel;
	}

	public RadiatorModel getRadiatorModel() {
		return radiatorModel;
	}
}
