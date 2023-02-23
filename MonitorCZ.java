package practica2.P0CZ.Monitor;

import java.util.concurrent.locks.ReentrantLock;

public class MonitorCZ {

    private int x = 0;
    private final int I = 10;

    public void inc() {
        //Incrementa en una unitat el valor d'x
        ReentrantLock l=new ReentrantLock();
        for (int i = 0; i < I; i++) {
            l.lock();
            try{
                x = x + 1;
            }finally{
                l.unlock();
            }
        }
    }

    public int getX() {
        //Ha de retornar el valor d'x
        return x;
    }

}
