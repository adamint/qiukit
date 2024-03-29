package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class SPhase extends QubitUnaryOperator<Qubit, Qubit> {
  public SPhase(Random random) {
    super("S", random, "qubit");
  }

  public SPhase() {
    this(new Random());
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return argument.phase(Math.PI / 4);
  }
}
