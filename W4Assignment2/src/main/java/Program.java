public class Program {

    private Thread[] guests;

    private class Philosopher extends Thread
    {
        private int id = -1;
        Philosopher(int id)
        {
            this.id = id;
        }

        public void run() {
            System.out.println(String.format("Philosopher %d",id));
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

    private void cleanup() throws Exception
    {
        for(int i=0; i<guests.length; i++)
            guests[i].join();
    }
    
    private void serve(int guestCount) throws Exception
    {
        guests = new Thread[guestCount];
        for( int i=0; i<guestCount; i++ )
        {
            guests[i] = new Philosopher(i+1);
            guests[i].start();
        }
    }        
}
