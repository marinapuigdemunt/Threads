package practica1.Protocol;

import static java.lang.Integer.min;
import util.TCPSegment;
import util.TSocket_base;
import util.SimNet;

public class TSocketRecv extends TSocket_base {

  public TSocketRecv(SimNet net) {
    super(net);
  }

  @Override
  public int receiveData(byte[] data, int offset, int length) {
    TCPSegment seg=network.receive();
    for(int i=0; i<seg.getDataLength();i++){
        data[i+offset]=seg.getData()[i];
    }
     System.out.println("\t\t\t\t\t\t\t\tReceived:"+seg);
        return min(seg.getDataLength(),length);
  }
}
