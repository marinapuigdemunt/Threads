package practica1.LinkedQ;

import java.util.Arrays;
import practica1.CircularQ.CircularQueue;

public class TestLQ {

  public static void main(String[] args) {
    LinkedQueue<Integer> q = new LinkedQueue<>();
    q.put(1);
    q.put(2);
    q.put(3);
    q.put(4);
    q.put(5);
    System.out.println("El nombre d'elements és:"+q.size());
      System.out.println("Tenim "+q.free()+"elements lliures");
      System.out.println("La cola está vacía? "+q.empty());
      System.out.println("La cola está llena? "+q.full());
      System.out.println("El primer elemento de la cola es: "+q.peekFirst());
      System.out.println("La cabeza de la cola es: "+q.get());
      System.out.println(q.toString());
  }
}
