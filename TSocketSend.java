package practica3;

import util.Const;
import util.TCPSegment;
import util.TSocket_base;
import util.SimNet;

public class TSocketSend extends TSocket_base {

  protected int MSS;       // Maximum Segment Size

  public TSocketSend(SimNet net) {
    super(net);
    MSS = net.getMTU() - Const.IP_HEADER - Const.TCP_HEADER;
  }

  @Override
  public void sendData(byte[] data, int offset, int length) {
    //s'agafa seq de bytes prov de les dades de l'aplicació
    //es fragmenta
    //s'encapsula en un o més TCPseg de mida max segons MTU
    
    TCPSegment seg;
    int enviats=0;
    int i=0;
    int pendents=length-enviats;
    while(enviats<length){//queden per enviar bytes
        if(pendents>=MSS){
            seg=segmentize(data,offset+i*MSS,MSS);
            enviats+=MSS;
            pendents=length-enviats;
        }else{
            seg=segmentize(data,offset+i*MSS,pendents);
        }
        i++;
        //segs senvien pel nivell de xarxa
        network.send(seg);
            System.out.println("snd--> "+seg);
        } 
    }

  protected TCPSegment segmentize(byte[] data, int offset, int length) {
    //creem nou segments tcp
    TCPSegment tcp=new TCPSegment();
    //creem un nou seg de bytes del tamany q segmentarem
    byte[] seg = new byte[length];
    for(int i=0;i<length;i++){
        seg[i]=data[i+offset];
    }
    //activar el flag de puch pq es un seg de dades
    tcp.setData(seg);
    tcp.setPsh(true);
    //enviar el segment a la xarxa --> network
    return tcp;
  }
//sendData decideix quants cops he de cridar a segmentize i amb quins parametres l'he de cridar
}
