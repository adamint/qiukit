package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class Not extends QubitUnaryOperator<Qubit, Qubit> {
  public Not(Random random) {
    super("Not", random);
  }

  public Not() {
    super("Not");
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return new Qubit(argument.getOne(), argument.getZero(), argument.getRandom());
  }
}