package practica2.Protocol;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import practica1.CircularQ.CircularQueue;
import util.Const;
import util.TCPSegment;
import util.SimNet;

public class SimNet_Monitor implements SimNet {

  protected CircularQueue<TCPSegment> queue;
  ReentrantLock l;
  Condition c_cua_plena, c_cua_buida;

  public SimNet_Monitor() {
    queue  = new CircularQueue<>(Const.SIMNET_QUEUE_SIZE);
    l = new ReentrantLock();
    c_cua_plena = l.newCondition();
    c_cua_buida = l.newCondition();
  }

  @Override
  public void send(TCPSegment seg) {
     l.lock();
        try{
            while(queue.full()){c_cua_plena.awaitUninterruptibly();}
            queue.put(seg);
            c_cua_buida.signal();
        }
        finally{
            l.unlock();
        }
  }

  @Override
  public TCPSegment receive() {
    TCPSegment tmp;
        l.lock();
        try{
            while(queue.empty()){c_cua_buida.awaitUninterruptibly();}
            tmp = queue.get();
            c_cua_plena.signal();
        }
        finally{
            l.unlock();
        }
        return tmp;
  }

  @Override
  public int getMTU() {
      return Const.MTU_ETHERNET;
  }
}
