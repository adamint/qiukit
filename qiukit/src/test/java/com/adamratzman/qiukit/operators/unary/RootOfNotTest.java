package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Assert;
import org.junit.Test;

public class RootOfNotTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO);

  @Test
  public void evaluate() {
    Assert.assertEquals(zero.hadamard().phaseDegrees(90).hadamard(), zero.rootOfNot());
    Assert.assertEquals(zero.not(), zero.rootOfNot().rootOfNot());

    System.out.println(zero.not());
    System.out.println(zero.rootOfNot());
    System.out.println(zero.rootOfNot().rootOfNot());
  }
}