package temperaturemvvm.radiator;

public class OffState implements RadiatorState {
	private static final int POWER = 0;
	@Override
	public void turnUp(Radiator r) {
		r.setPowerState(new Power1State());
	}

	@Override
	public void turnDown(Radiator r) {
		// empty
	}

	@Override
	public int getPower() {
		return POWER;
	}
}
