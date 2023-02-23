package practica2.P1Sync.Monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorSync {

    private final int N;
    ReentrantLock l=new ReentrantLock();
    Condition emtoca=l.newCondition();
    int torn=0;  //torn és l'id, si torn=0, 0 ha de mostrar en pantalla
    
    public MonitorSync(int N) {
        this.N = N;
    }

    public int getN() {
        return N;
    }

    public void waitForTurn(int id) {
        l.lock();
        try{
            while(id!=torn){emtoca.awaitUninterruptibly();} 
        }finally{
            l.unlock();    
        }
    }

    public void transferTurn() {
        l.lock();
        try{
            if(torn==0){
                torn=1;
            }
            else{
                torn=0;
            }
            emtoca.signal();
        }finally{
            l.unlock();    
        }
        
    } 
}
