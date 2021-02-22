package temperaturemvvm.core;

import temperaturemvvm.mediator.TemperatureModel;
import temperaturemvvm.mediator.TemperatureModelManager;

public class ModelFactory {
	private TemperatureModel temperatureModel;

	public ModelFactory() {
		temperatureModel = new TemperatureModelManager();
	}

	public TemperatureModel getTemperatureModel() {
		return temperatureModel;
	}
}
