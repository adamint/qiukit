package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO);
  private Qubit one = Qubit.getQubit(Qubit.State.ONE);
  private Qubit plus = Qubit.getPlusQubit();

  @Test
  public void evaluate() {
    Assert.assertEquals(zero, zero.not().not());
    Assert.assertEquals(one, zero.not());
    Assert.assertEquals(plus, plus.not().not());

  }
}