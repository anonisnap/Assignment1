package temperaturemvvm.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import temperaturemvvm.view.ViewController;
import temperaturemvvm.view.graph.GraphViewController;
import temperaturemvvm.view.temperature.TemperatureViewController;

public class ViewHandler extends Application {
	private Stage primaryStage;
	private Scene currentScene;
	private ViewModelFactory viewModelFactory;

	public ViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		currentScene = new Scene(new Region());
		openView("temperature");
		this.primaryStage.setOnCloseRequest(e -> System.exit(1));
	}

	public void openView(String id) {
		Region root = null;
		ViewController controller;
		switch (id) {
			case "temperature":
				controller = new TemperatureViewController();
				root = loadView(id, controller, "../view/temperature/TemperatureView.fxml");
				break;
			case "graph":
				controller = new GraphViewController();
				root = loadView(id, controller, "../view/graph/graphView.fxml");
				break;
		}
		currentScene.setRoot(root);

		String title = "";
		assert root != null;
		if (root.getUserData() != null) {
			title += root.getUserData();
		}
		primaryStage.setTitle(title);
		primaryStage.setScene(currentScene);
		primaryStage.setWidth(root.getPrefWidth());
		primaryStage.setHeight(root.getPrefHeight());
		primaryStage.show();
	}

	private Region loadView(String caseStr, ViewController controller, String path){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));
			Region root = loader.load();
			controller = loader.getController();
			controller.init(this, viewModelFactory.getViewModel(caseStr), root);
			return controller.getRoot();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void close() {
		primaryStage.close();
		System.exit(1);
	}
}
