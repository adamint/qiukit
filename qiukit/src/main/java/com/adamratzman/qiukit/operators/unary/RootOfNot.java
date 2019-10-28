package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class RootOfNot extends QubitUnaryOperator<Qubit> {
  public RootOfNot(Random random) {
    super("RootOfNot", random);
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return argument.hadamard().phase(Math.PI / 2).hadamard();
  }
}
