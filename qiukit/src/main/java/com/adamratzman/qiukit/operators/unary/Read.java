package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;

import java.util.Random;

public class Read extends QubitUnaryOperator<Qubit> {
  public Read(Random random) {
    super(random);
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    double oneProbability = argument.getOneVector().getProbability();

    if (argument.getRandom().nextDouble() < oneProbability) return Qubit.getOne(argument.getRandom());
    else return Qubit.getZero(argument.getRandom());

  }
}
