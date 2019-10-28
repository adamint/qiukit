package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Test;

public class PhaseTest {
  @Test
  public void evaluate() {
    System.out.println(Qubit.getQubit(Qubit.State.ZERO).hadamard());
    System.out.println(Qubit.getQubit(Qubit.State.ZERO).hadamard().phaseDegrees(90));
    System.out.println(Qubit.getQubit(Qubit.State.ZERO).hadamard().phaseDegrees(90).hadamard());


 //   System.out.println(Qubit.getQubit(Qubit.State.ZERO).hadamard().phaseDegrees(270).hadamard().hadamard());
  }
}