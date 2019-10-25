package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Test;

public class PhaseTest {
  @Test
  public void evaluate() {
    System.out.println(Qubit.getQubit(Qubit.State.ZERO));
    System.out.println(Qubit.getQubit(Qubit.State.ZERO).hadamard());
    System.out.println(Qubit.getQubit(Qubit.State.ONE));
    System.out.println(Qubit.getQubit(Qubit.State.ONE).hadamard());
  }
}