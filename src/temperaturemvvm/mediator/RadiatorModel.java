package temperaturemvvm.mediator;

import temperaturemvvm.radiator.Radiator;
import temperaturemvvm.util.Subject;

public interface RadiatorModel extends Subject {
	void turnUp();
	void turnDown();
	int getPower();
	Radiator getRadiator();
}
