public class Program {

	private Philosopher[] guests;

	private class Chopstick {
		boolean inUse = false;

		synchronized public void pickUpChopstick(int id, String name)
				throws Exception {
			if (inUse)
				wait();
			System.out.println(String.format(
					"Philosopher %d picks up %s chopstick.", id, name));
			inUse = true;
		}

		synchronized void setDownChopstick(int id, String name) {
			if (inUse) {
				System.out.println(String.format(
						"Philosopher %d sets down %s chopstick.", id, name));
				inUse = false;
				notify();
			}
		}
	};

	private class Philosopher extends Thread {
		private int bitesLeft = 5;
		private int id = -1;
		private Chopstick left;
		private Chopstick right;

		Philosopher(int id, Chopstick left, Chopstick right) {
			this.id = id;
			this.left = left;
			this.right = right;
		}

		public Chopstick getRightChopstick()
		{
			return right;
		}
		
		public void run() {
			while (bitesLeft > 0) {
				try {
					left.pickUpChopstick(id, "left");
					right.pickUpChopstick(id, "right");
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(String.format("Philosopher %d eats.", id));
				bitesLeft--;
				right.setDownChopstick(id, "right");
				left.setDownChopstick(id, "left");
			}
		}

		public void setLeftChopstick(Chopstick leftChopstick) {
			this.left = leftChopstick;
		}
	};

	public static void main(String[] args) throws Exception {

		System.out.println("Dinner is starting!");
		System.out.println("");

		Program dinner = new Program();
		dinner.serve(5);
		dinner.cleanup();

		System.out.println("Dinner is over!");
	}

	private void cleanup() throws Exception {
		for (int i = 0; i < guests.length; i++)
			guests[i].join();
	}

	private void serve(int guestCount) throws Exception {
		guests = new Philosopher[guestCount];
		Chopstick left = new Chopstick();
		for (int i = 0; i < guestCount; i++) {
			Chopstick right = new Chopstick();
			guests[i] = new Philosopher(i + 1, left, right);
			left = right;
		}
		//Setup the last philosopher's left chopstick with the first one's right chopstick
		int lastGuest = guestCount-1;
		int firstGuest = 0;		
		guests[lastGuest].setLeftChopstick ( guests[firstGuest].getRightChopstick() );
		
		for (int i=0; i< guestCount; i++)
			guests[i].start();
	}
}
