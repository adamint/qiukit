package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Test;

public class RootOfNotTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO);

  @Test
  public void evaluate() {
    System.out.println(zero);
    System.out.println(zero.hadamard());
    System.out.println(zero.hadamard().phase(Math.PI / 4));
    System.out.println(zero.hadamard().phase(Math.PI  / 4).hadamard());
  }
}