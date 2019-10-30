package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class TPhase extends QubitUnaryOperator<Qubit, Qubit> {
  public TPhase(Random random) {
    super("T", random);
  }

  public TPhase() {
    super("T");
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return argument.phase(Math.PI / 8);
  }
}
