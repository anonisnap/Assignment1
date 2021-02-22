package temperaturemvvm.view;

import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.temperature.TemperatureViewModel;

public interface ViewController {
	void init(ViewHandler viewHandler, TemperatureViewModel temperatureViewModel, Region root);
	void swapScene(ActionEvent actionEvent);
	Region getRoot();
}
