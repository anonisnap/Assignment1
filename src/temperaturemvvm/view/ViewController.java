package temperaturemvvm.view;

import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import temperaturemvvm.core.ViewHandler;

public interface ViewController {
	void init(ViewHandler viewHandler, ViewModel temperatureViewModel, Region root);

	void swapScene(ActionEvent actionEvent);

	Region getRoot();

	void close();
}
