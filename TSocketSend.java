package practica1.Protocol;

import util.TCPSegment;
import util.TSocket_base;
import util.SimNet;

public class TSocketSend extends TSocket_base {

  public TSocketSend(SimNet net) {
    super(net);
  }

  @Override
  public void sendData(byte[] data, int offset, int length) {
      TCPSegment seg=new TCPSegment();
    //offset posició dins de data on comencen els bytes útils
    //ength que és el número de posicions útils
    byte[] seg_data = new byte[length];
    for(int i=0;i<length;i++){
        seg_data[i]=data[i+offset];
    }
    seg.setData(seg_data);
    seg.setPsh(true);
    network.send(seg);
      System.out.println("Send:"+seg);
  }
}
