package practica2.P0CZ.Monitor;

public class TestSumCZ {

    public static void main(String[] args) throws InterruptedException {
        MonitorCZ m=new MonitorCZ();
        CounterThreadCZ a= new CounterThreadCZ(m); 
        CounterThreadCZ b= new CounterThreadCZ(m); 
            m.inc();
            System.out.println("X val:"+m.getX());
    }
}
