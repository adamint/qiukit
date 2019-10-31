package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Assert;
import org.junit.Test;

public class PauliYTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO);

  @Test
  public void evaluate() {
    Assert.assertEquals(Qubit.getQubit(Qubit.State.ONE).phase(Math.PI * 1 / 2), zero.pauliY());
    Assert.assertEquals(zero.hadamard().phaseZero(Math.PI * 3 / 2).phase(Math.PI * 1 / 2), zero.hadamard().pauliY());
  }
}