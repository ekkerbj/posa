public class Program {

	private static final int TOTAL_ROUNDS = 6;
	private volatile int rounds = 0;
	private volatile boolean turn = true;

	public synchronized void ping() {
		try {
			if (turn) {
				rounds++;
				System.out.println("Ping!");
				turn = false;
			}
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void pong() {
		if (!turn) {
			rounds++;
			System.out.println("Pong!");
			turn = true;
		}
		notify();
	}

	public static void main(String[] args) throws Exception {
		
		System.out.print("Ready... ");
		final Program game = new Program();

		System.out.print("Set... ");
		Thread ping = new Thread() {
			public void run() {
				while (game.rounds < game.TOTAL_ROUNDS)
					game.ping();
			}
		};
		Thread pong = new Thread() {
			public void run() {
				while (game.rounds < game.TOTAL_ROUNDS)
					game.pong();
			}
		};
				
		System.out.println("Go!");
		System.out.println("");
				
		ping.start();
		pong.start();
		
		ping.join();
		pong.join();
		
		System.out.println("Done!");
	}
}
