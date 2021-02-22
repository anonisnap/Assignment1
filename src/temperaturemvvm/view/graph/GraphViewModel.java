package temperaturemvvm.view.graph;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperaturemvvm.mediator.TemperatureModel;
import temperaturemvvm.mediator.TemperatureModelManager;
import temperaturemvvm.view.ViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GraphViewModel implements PropertyChangeListener, ViewModel {
	private TemperatureModelManager temperatureModel;
	private IntegerProperty temperatureProperty;
	private StringProperty id;
	// TODO: Figure out, how to get data from the lists

	public GraphViewModel(TemperatureModel temperatureModel) {
		this.temperatureModel = (TemperatureModelManager) temperatureModel;
		temperatureModel.addListener("Temperature", this);
		id = new SimpleStringProperty();
	}

	public IntegerProperty temperatureProperty() {
		return temperatureProperty;
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
