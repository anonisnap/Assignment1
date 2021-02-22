package temperaturemvvm.core;

import temperaturemvvm.view.temperature.TemperatureViewModel;

public class ViewModelFactory {
	private TemperatureViewModel temperatureViewModel;
	private ModelFactory modelFactory;

	public ViewModelFactory(ModelFactory modelFactory) {
		this.modelFactory = modelFactory;
		temperatureViewModel = new TemperatureViewModel(modelFactory.getTemperatureModel(),modelFactory.getRadiatorModel());
	}

	public TemperatureViewModel getTemperatureViewModel() {
		return temperatureViewModel;
	}
}
