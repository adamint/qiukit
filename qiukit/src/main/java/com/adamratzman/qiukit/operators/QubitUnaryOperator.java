package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.qubit.Qubit;

import java.util.Random;

public abstract class QubitUnaryOperator<T> extends QubitOperator {
  public QubitUnaryOperator(Random random) {
    super(random);
  }

  public abstract Qubit evaluate(T argument);
}
