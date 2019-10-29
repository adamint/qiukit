package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class RootOfNot extends QubitUnaryOperator<Qubit, Qubit> {
  public RootOfNot(Random random) {
    super("RootOfNot", random);
  }

  public RootOfNot() {
    super("RootOfNot");
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return argument.hadamard().phase(Math.PI / 2).hadamard();
  }
}
