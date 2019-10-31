package com.adamratzman.qiukit.operators.two.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.utils.Pair;
import org.junit.Assert;
import org.junit.Test;

public class SwapTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO).hadamard().phase(Math.PI);
  private Qubit one = Qubit.getQubit(Qubit.State.ONE).rootOfNot().phaseDegrees(279);
  private Swap swap = new Swap();

  @Test
  public void evaluate() {
    Assert.assertEquals(new Pair<>(zero, one), swap.evaluate(new Pair<>(one, zero)));
  }
}