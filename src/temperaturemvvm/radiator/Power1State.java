package temperaturemvvm.radiator;

public class Power1State implements RadiatorState {
	private static final int POWER = 1;

	@Override
	public void turnUp(Radiator r) {
		r.setPowerState(new Power2State());
	}

	@Override
	public void turnDown(Radiator r) {
		r.setPowerState(new OffState());
	}

	@Override
	public int getPower() {
		return POWER;
	}
}
