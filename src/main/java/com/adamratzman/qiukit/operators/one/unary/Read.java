package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class Read extends QubitUnaryOperator<Qubit, Qubit> {
  public Read(Random random) {
    super("Read", random, "qubit");
  }

  public Read() {
    this(new Random());
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    double oneProbability = argument.getProbability(Qubit.State.ONE);

    if (argument.getRandom().nextDouble() < oneProbability)
      return Qubit.getQubit(Qubit.State.ONE, argument.getRandom());
    else return Qubit.getQubit(Qubit.State.ZERO, argument.getRandom());

  }
}
