package practica4;

import util.Protocol_base;
import util.TCPSegment;
import util.SimNet;
import util.TSocket_base;

public class Protocol extends Protocol_base {

    public Protocol(SimNet net) {
      super(net);
    }

    protected void ipInput(TCPSegment seg) {
        getMatchingTSocket(seg.getSourcePort(),seg.getDestinationPort());
        
    }

    protected TSocket_base getMatchingTSocket(int localPort, int remotePort) {
        lk.lock();
        try {
            int i=0;
            boolean trobat=false;
            while(!trobat && i<activeSockets.size()){
                if(activeSockets.get(i).localPort==remotePort&&activeSockets.get(i).remotePort==localPort){
                    trobat=true;
                    return activeSockets.get(i);
                }
                i++;
            }
        return null;    
        }finally{
            lk.unlock();
        }
    }
}
