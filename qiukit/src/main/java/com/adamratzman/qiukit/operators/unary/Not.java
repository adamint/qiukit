package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.qubit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class Not extends QubitUnaryOperator<Qubit> {
  public Not(Random random) {
    super("Not", random);
  }

  @Override
  public Qubit evaluate(Qubit argument) {
      return new Qubit(argument.getOne(), argument.getZero(), argument.getRandom());
  }
}
