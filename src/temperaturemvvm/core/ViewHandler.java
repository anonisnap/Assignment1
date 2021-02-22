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
				root = loadView(controller, "../view/temperature/TemperatureView.fxml");
				break;
			case "graph":
				controller = new GraphViewController();
				root = loadView(controller, "../view/graph/graphView.fxml");
				break;
		}
		currentScene.setRoot(root);

		String title = "";
		if (root.getUserData() != null) {
			title += root.getUserData();
		}
		primaryStage.setTitle(title);
		primaryStage.setScene(currentScene);
		primaryStage.setWidth(root.getPrefWidth());
		primaryStage.setHeight(root.getPrefHeight());
		primaryStage.show();
	}

	private Region loadTemperatureView(String fxmlFile) {
		TemperatureViewController temperatureViewController;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(fxmlFile));
			Region root = loader.load();
			temperatureViewController = loader.getController();
			temperatureViewController.init(this, viewModelFactory.getTemperatureViewModel(), root);
			return temperatureViewController.getRoot();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Region loadView(ViewController controller, String path){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));
			Region root = loader.load();
			controller = loader.getController();
			controller.init(this, viewModelFactory.getTemperatureViewModel(), root);
			return controller.getRoot();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
