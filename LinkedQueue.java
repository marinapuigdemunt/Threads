package practica1.LinkedQ;

import java.util.Iterator;
import util.Queue;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> primer,ultim;
    private int numelem;
    
  @Override
  public int size() {
    return numelem;
  }

  @Override
  public int free() {
    return -1; //siempre free por lo q no tiene sentido
  }

  @Override
  public boolean empty() {
    return numelem==0;
  }

  @Override
  public boolean full() {
    return false;
  }

  @Override
  public E peekFirst() {
    if(empty()){
        return null;
    }
    return primer.getValue();
  }
   
  @Override
  public E get() {
    if(empty()){
        throw new IllegalStateException("Cua buida");
    }
  E tmp=primer.getValue();
  primer=primer.getNext();
  numelem--;
  return tmp;
  }

  @Override
  public void put(E e) {
    Node<E> node= new Node(e,null);
    if(empty()){
        primer=node;
    }
    else{
        ultim.setNext(node);
  }
    ultim=node;
    numelem++;
  }

    @Override
   public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("[");
        Node<E> n=primer;
        if(numelem==0) return "[]";
        for(int i=0; i<numelem-1; i++){
            text.append(n.getValue()).append(", ");
            n=n.getNext();
        }
        text.append(n.getValue()).append("]");
        return text.toString();
    }

  @Override
  public Iterator<E> iterator() {
    return new MyIterator();
  }

  class MyIterator implements Iterator {

    private int numIteracions=0;
    boolean next=false;
    Node<E> ncons=primer, ncons2ant=primer, ncons1ant=primer,ncons3ant;
  
    @Override
    public boolean hasNext() {
       return numIteracions<numelem;
    }

    @Override
    public E next() {
        //retorna elem actual
      E elemconsultat=ncons.getValue();
      ncons3ant=ncons2ant;
      numIteracions++;
       if(numIteracions>1){
          ncons1ant=ncons1ant.getNext();
      }
      if(numIteracions>2){
          ncons2ant=ncons2ant.getNext();
      }
      ncons=ncons.getNext();
      next=true;
      
      //System.out.println("elemconsqborraremes"+elemconsultat+"   i ncons al q itero es"+ncons.getValue());
      
      return elemconsultat;
    }
    
    @Override
    public void remove() {
        //eliminem element retornat per l'ultim next
        if(!next){
            throw new IllegalStateException("next() has not been implemented)");
        }
        if(numIteracions==1){
            primer=ncons;
            ncons2ant=primer; //sha de canviar pq canviant primer les vars q fossin igual a primer no canvien, es queden amb lantic primer 
            ncons1ant=primer;
            
        }
        else if (numIteracions==numelem){
              ultim=ncons2ant; //no seria 2 ant
           
          
        }
        else{
            
            ncons2ant.setNext(ncons);
            ncons1ant=ncons2ant;
            ncons2ant=ncons3ant;
        }
        numIteracions--;
        numelem--;
        next=false;
    }

  }
}
