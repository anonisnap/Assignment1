package temperaturemvvm.core;

import temperaturemvvm.view.ViewModel;
import temperaturemvvm.view.graph.GraphViewModel;
import temperaturemvvm.view.temperature.TemperatureViewModel;

public class ViewModelFactory {
	private TemperatureViewModel temperatureViewModel;
	private GraphViewModel graphViewModel;
	private ModelFactory modelFactory;

	public ViewModelFactory(ModelFactory modelFactory) {
		this.modelFactory = modelFactory;
		temperatureViewModel = new TemperatureViewModel(this.modelFactory.getTemperatureModel(), modelFactory.getRadiatorModel());
		graphViewModel = new GraphViewModel(this.modelFactory.getTemperatureModel());
	}

	public ViewModel getViewModel(String caseStr) {
		switch (caseStr) {
			case "temperature":
				return temperatureViewModel;
			case "graph":
				return graphViewModel;
		}
		return null;
	}
}
