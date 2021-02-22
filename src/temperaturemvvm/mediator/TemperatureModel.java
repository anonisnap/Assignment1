package temperaturemvvm.mediator;

import temperaturemvvm.model.Temperature;
import temperaturemvvm.util.Subject;

public interface TemperatureModel extends Subject {
	void addTemperature(String id, double temperature);

	Temperature getLastInsertedTemperature();

	Temperature getLastInsertedTemperature(String id);
}
