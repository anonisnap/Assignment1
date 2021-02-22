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

	public int getPower() {
		return currentState.getPower();
	}

	void setPowerState(RadiatorState state) {
		currentState = state;
	}

}
