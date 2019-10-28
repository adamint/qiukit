package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class PauliZ extends QubitUnaryOperator<Qubit> {
  public PauliZ(Random random) {
    super("Z", random);
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return argument.phase(Math.PI);
  }
}
