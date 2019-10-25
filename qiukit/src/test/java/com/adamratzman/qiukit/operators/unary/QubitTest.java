package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Test;

public class QubitTest {
  @Test
  public void test() {
    Qubit qubit = Qubit.getQubit(Qubit.State.ZERO);
    System.out.println(qubit.hadamard());
    System.out.println(qubit.hadamard().phase(Math.PI / 3));
  }
}
