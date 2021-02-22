package temperaturemvvm;

import javafx.application.Application;
import javafx.stage.Stage;
import temperaturemvvm.core.ModelFactory;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.core.ViewModelFactory;
import temperaturemvvm.external.Thermometer;
import temperaturemvvm.mediator.TemperatureModel;

public class TemperatureApp extends Application {
	public void start(Stage primaryStage) {
		// Model
		ModelFactory modelFactory = new ModelFactory();

		// Thermometer
		TemperatureModel temperatureModel = modelFactory.getTemperatureModel();
		Thermometer therm1 = new Thermometer(temperatureModel, "t1", 20);
		Thermometer therm2 = new Thermometer(temperatureModel, "t2", 205);
		therm1.setDistanceFromHeater(1);
		therm2.setDistanceFromHeater(2);
		Thread t1 = new Thread(therm1);
		Thread t2 = new Thread(therm2);
		t1.start();
		t2.start();

		// View
		ViewHandler view = new ViewHandler(new ViewModelFactory(modelFactory));
		view.start(primaryStage);
	}
}
