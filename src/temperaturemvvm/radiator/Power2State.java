package temperaturemvvm.radiator;

public class Power2State implements RadiatorState {
	private static final int POWER = 2;

	@Override
	public void turnUp(Radiator r) {
		r.setPowerState(new Power3State(r));
	}

	@Override
	public void turnDown(Radiator r) {
		r.setPowerState(new Power1State());
	}

	@Override
	public int getPower() {
		return POWER;
	}
}
