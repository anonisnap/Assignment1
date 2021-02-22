package temperaturemvvm.radiator;

public class Power3State implements RadiatorState {
	private static final int POWER = 3;
	Thread t;

	public Power3State(Radiator r) {
		t = new Thread(() -> {
				try {
					Thread.sleep(10000);
					r.setPowerState(new Power2State());
				} catch (InterruptedException e) {
					System.out.println("Manually turned off by Use");
			}
		});
		t.start();
	}

	@Override
	public void turnUp(Radiator radiator) {
		// empty
	}

	@Override
	public void turnDown(Radiator radiator) {
		t.interrupt();
		radiator.setPowerState(new Power2State());
	}

	@Override
	public int getPower() {
		return POWER;
	}
}
