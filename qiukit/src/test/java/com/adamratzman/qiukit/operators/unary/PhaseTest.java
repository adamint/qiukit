package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Test;

public class PhaseTest {
  private Qubit zeroHad = Qubit.getQubit(Qubit.State.ZERO).hadamard();

  @Test
  public void evaluate() {
    System.out.println(zeroHad);
    System.out.println(zeroHad.phase(Math.PI));
  }
}