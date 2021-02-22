package temperaturemvvm.radiator;

public class Radiator {
	private RadiatorState currentState;

	public Radiator() {
		this.currentState = new OffState();
	}

	public void turnUp() {
		currentState.turnUp(this);
	}

	public void turnDown() {
		currentState.turnDown(this);
	}

	public void getPower() {
		int power = currentState.getPower();
		System.out.println("Current Power output: " + power);
	}

	void setPowerState(RadiatorState state) {
		currentState = state;
	}

}
