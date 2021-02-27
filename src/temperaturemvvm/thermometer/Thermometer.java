package temperaturemvvm.thermometer;

import temperaturemvvm.mediator.TemperatureModel;
import temperaturemvvm.radiator.Radiator;

public class Thermometer implements Runnable {
	private TemperatureModel model;
	private String id;
	private double currentTemperature;
	private int distanceFromHeater;
	private Radiator radiator;
	private int timeSinceLastMeasure;
	private double outdoorTemperature;

	public Thermometer(TemperatureModel model, String id, double currentTemperature, Radiator radiator) {
		this.model = model;
		this.id = id;
		this.currentTemperature = currentTemperature;
		this.radiator = radiator;
		distanceFromHeater = 0;
		timeSinceLastMeasure = 1;
		outdoorTemperature = 0;
	}

	public void setDistanceFromHeater(int distanceFromHeater) {
		this.distanceFromHeater = distanceFromHeater;
	}

	public double temperature(int heatingPower, int distanceFromHeater, double outdoorTemperature) {
		double tMax = Math.min(11 * heatingPower + 10, 11 * heatingPower + 10 + outdoorTemperature);
		tMax = Math.max(Math.max(currentTemperature, tMax), outdoorTemperature);
		double heaterTerm = 0;
		if (heatingPower > 0) {
			double den = Math.max((tMax * (20 - 5 * heatingPower) * (distanceFromHeater + 5)), 0.1);
			heaterTerm = 30 * timeSinceLastMeasure * Math.abs(tMax - currentTemperature) / den;
		}
		double outdoorTerm = (currentTemperature - outdoorTemperature) * timeSinceLastMeasure / 250.0;
		currentTemperature = Math.min(Math.max(currentTemperature - outdoorTerm + heaterTerm, outdoorTemperature), tMax);
		return currentTemperature;
	}

	@Override
	public void run() {
		int heatingPower;
		while (true) {
			heatingPower = radiator.getPower();
			currentTemperature = temperature(heatingPower, distanceFromHeater, outdoorTemperature);
			model.addTemperature(id, currentTemperature);
			try {
				Thread.sleep(timeSinceLastMeasure * 1000);
				timeSinceLastMeasure = (int) (4 + Math.random() * 4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
