package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class PauliZ extends QubitUnaryOperator<Qubit, Qubit> {
  public PauliZ(Random random) {
    super("Z", random);
  }

  public PauliZ() {
    super("Z");
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return argument.phase(Math.PI);
  }
}
