package practica2.P0CZ;

public class TestSum {

    public static void main(String[] args) throws InterruptedException {
            CounterThread a= new CounterThread(); 
            CounterThread b= new CounterThread(); 
            a.start();
            b.start();
            //a.run(); 
            //b.run(); NO CAL!!!!!!! PQ EL START JA ET FA EL RUN!!!
            a.join();
            b.join();
            System.out.println("X val:"+CounterThread.x);
    }
}
