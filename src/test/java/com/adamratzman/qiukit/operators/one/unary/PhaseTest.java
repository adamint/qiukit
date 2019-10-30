package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Assert;
import org.junit.Test;

public class PhaseTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO);
  @Test
  public void evaluate() {
    Assert.assertEquals(zero, zero.phaseDegrees(360));
    Assert.assertEquals(zero, zero.phaseDegrees(180).phaseDegrees(180));
  }
}