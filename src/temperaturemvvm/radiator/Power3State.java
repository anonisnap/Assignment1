package temperaturemvvm.radiator;

public class Power3State implements RadiatorState {
	private static final int POWER = 3;
	Thread t;

	public Power3State(Radiator r) {
		t = new Thread(() -> {
				try {
					Thread.sleep(40000);
					r.setPowerState(new Power2State());
				} catch (InterruptedException e) {
					// Likely turned off by User
			}
		});
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void turnUp(Radiator r) {
		// empty
	}

	@Override
	public void turnDown(Radiator r) {
		t.interrupt();
		r.setPowerState(new Power2State());
	}

	@Override
	public int getPower() {
		return POWER;
	}
}
