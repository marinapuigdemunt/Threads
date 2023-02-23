package practica1.CircularQ;

import java.util.Iterator;
import util.Queue;

public class CircularQueue<E> implements Queue<E> {

    private final E[] queue;
    private final int N;
    private int numelem,G,P;

    public CircularQueue(int N) {
        this.N = N;
        queue = (E[]) (new Object[N]);
    }

    @Override
    public int size() {
        return numelem;
    }

    @Override
    public int free() {
        return (N-numelem);
    }

    @Override
    public boolean empty() {
        return numelem==0;   
    }

    @Override
    public boolean full() {
        return numelem==N;
    }

    @Override
    public E peekFirst() {
        if(empty()){
            return null;
        }
        return queue[G];
    }

    @Override
    public E get() {
      if(empty()){
         throw new IllegalStateException("This queue is empty");
     }
      E e = queue[G];
      queue[G] = null;
      G++;
      numelem--;
      return e;
    }

    @Override
    public void put(E e) {
       if(full()){
        throw new IllegalStateException("This queue is full");
       }
       queue[P]=e;
       P++;
       P=P%N;
       numelem++;
       
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("[");
        int a =G;
        for(int i=0; i<numelem-1; i++){
            text.append(queue[a]).append(", ");
            a++;
            a=a%N;
        }
        text.append(queue[a]).append("]");
        return text.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {
        private int numIteracions;
        boolean next=false;

        @Override
        public boolean hasNext() {
            return numIteracions<numelem;
        }

        @Override
        public E next() {
            E elemconsultat=queue[(G+numIteracions)%N];
            numIteracions++;
            next=true;
            return elemconsultat;
        }

        @Override
        public void remove() {
            int E1=(G+numIteracions-1)%N;
            if(!next){
                throw new IllegalStateException("next() has not been implemented");
            }
            for(int i=E1; i<numelem-1; i++){
                queue[i]=queue[i+1];
            }
            P--;
            numIteracions--;
            numelem--;
            next=false;
            
        }

    }
}
