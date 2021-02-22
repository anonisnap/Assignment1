package temperaturemvvm;

import javafx.application.Application;
import javafx.stage.Stage;
import temperaturemvvm.core.ModelFactory;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.core.ViewModelFactory;
import temperaturemvvm.radiator.Radiator;
import temperaturemvvm.thermometer.Thermometer;
import temperaturemvvm.mediator.TemperatureModel;

public class TemperatureApp extends Application {
	public void start(Stage primaryStage) {
		// Model
		ModelFactory modelFactory = new ModelFactory();

		// Radiator - 1 Radiator
		Radiator radiator = new Radiator();
		Radiator outdoorRadiator = new Radiator();

		// Thermometer - 2 Thermometers
		Thermometer therm1 = new Thermometer(modelFactory.getTemperatureModel(),"t1",10, radiator);
		therm1.setDistanceFromHeater(1);
		Thermometer therm2 = new Thermometer(modelFactory.getTemperatureModel(),"t2",10, radiator);
		therm2.setDistanceFromHeater(7);
		Thermometer outdoor = new Thermometer(modelFactory.getTemperatureModel(), "outdoor", 0, outdoorRadiator);

		// Thermometer Threads
		Thread t1 = new Thread(therm1);
		Thread t2 = new Thread(therm2);
		t1.start();
		t2.start();

		// View
		ViewHandler view = new ViewHandler(new ViewModelFactory(modelFactory));
		view.start(primaryStage);
	}
}
