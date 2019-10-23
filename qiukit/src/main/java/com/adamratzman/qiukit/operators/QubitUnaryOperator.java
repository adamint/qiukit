package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.qubit.Qubit;

import java.util.List;
import java.util.Random;

public abstract class QubitUnaryOperator<T> extends QubitOperator<T, Qubit> {
  public QubitUnaryOperator(String name, Random random) {
    super(name, random);
  }

  @Override
  public Qubit apply(List<T> arguments) {
    return evaluate(arguments.get(0));
  }

  public abstract Qubit evaluate(T argument);

}
