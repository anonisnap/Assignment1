package temperaturemvvm.external;

import temperaturemvvm.mediator.TemperatureModel;

public class Thermometer implements Runnable {
	private TemperatureModel model;
	private String id;
	private double temperature;
	private int distanceFromHeater;
	private int heatingPower;
	private int timeSinceLastMeasure;
	private double outdoorTemperature;

	public Thermometer(TemperatureModel model, String id, double temperature) {
		this.model = model;
		this.id = id;
		this.temperature = temperature;

		distanceFromHeater = 1;
		heatingPower = 2;
		timeSinceLastMeasure = 1;
		outdoorTemperature = 0;
	}

	public void setDistanceFromHeater(int distanceFromHeater) {
		this.distanceFromHeater = distanceFromHeater;
	}

	public double temperature(int heatingPower, int distanceFromHeater, double outdoorTemperature) {
		double tMax = Math.min(11 * heatingPower + 10, 11 * heatingPower + 10 + outdoorTemperature);
		tMax = Math.max(Math.max(temperature, tMax), outdoorTemperature);
		double heaterTerm = 0;
		if (heatingPower > 0) {
			double den = Math.max((tMax * (20 - 5 * heatingPower) * (distanceFromHeater + 5)), 0.1);
			heaterTerm = 30 * timeSinceLastMeasure * Math.abs(tMax - temperature) / den;
		}
		double outdoorTerm = (temperature - outdoorTemperature) * timeSinceLastMeasure / 250.0;
		temperature = Math.min(Math.max(temperature - outdoorTerm + heaterTerm, outdoorTemperature), tMax);
		return temperature;
	}

	@Override
	public void run() {
		while (true) {
			temperature = temperature(heatingPower, distanceFromHeater, outdoorTemperature);
			model.addTemperature(id, temperature);
			try {
				Thread.sleep(timeSinceLastMeasure * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
