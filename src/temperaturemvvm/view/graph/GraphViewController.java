package temperaturemvvm.view.graph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import temperaturemvvm.core.ViewHandler;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.ViewModel;

public class GraphViewController implements ViewController {
	@FXML private VBox outerBox;
	private LineChart<Number, Number> temperatureGraph;
	private ViewHandler viewHandler;
	private GraphViewModel graphViewModel;
	private Region root;

	public void init(ViewHandler viewHandler, ViewModel graphViewModel, Region root) {
		this.viewHandler = viewHandler;
		this.graphViewModel = (GraphViewModel) graphViewModel;
		this.root = root;
		createLineChart("Measurements", "Temperature");
		temporaryPopulationMethod();
		outerBox.getChildren().add(temperatureGraph);
	}

	private void createLineChart(String xAxisLabel, String yAxisLabel) {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel(xAxisLabel);
		yAxis.setLabel(yAxisLabel);
		temperatureGraph = new LineChart<>(xAxis, yAxis);
		temperatureGraph.setTitle("Temperature Over Time");
		temperatureGraph.setMaxHeight(1000);

	}
	/** Didn't work, pretty annoying to be completely honest */
	private void createSeries(String dataLabel, double[] valueList) {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName(dataLabel);
		for (int i = 0; i < valueList.length; i++) {
			series.getData().add(new XYChart.Data<>(i + 1, valueList[i]));
		}
		temperatureGraph.getData().add(series);
	}

	// TODO: Figure out how to Populate the Chart with Data from the Thermometers
	private void temporaryPopulationMethod() {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName("Temporary Test Numbers");
		for (int i = -200; i <= 200; i++) {
			series.getData().add(new XYChart.Data(i, formula(i))); // Formula currently has 3 possible graphs. #2 is kinda boring, probably not working right
		}
		temperatureGraph.getData().add(series);
	}

	private double formula(int i) {
		double x = i;
		switch (1) {
			case 1:
				return Math.sin(Math.PI * x / 5) - Math.tan(2 * x);
			case 2:
				return Math.min(Math.sin(Math.PI * x - 2), 0.452);
			case 3:
				return Math.sin(954 * x) - 2 * Math.cos(x);
		}
		return x;
	}
	// END

	@Override
	public void swapScene(ActionEvent actionEvent) {
		viewHandler.openView(((RadioMenuItem) actionEvent.getSource()).getText().toLowerCase());
	}

	@Override
	public Region getRoot() {
		return root;
	}

	@Override
	public void close() {
		viewHandler.close();
	}
}
