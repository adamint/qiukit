package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.qubit.Qubit;

import java.util.Random;

public abstract class QubitBinaryOperator<A, B> extends QubitOperator {
  public QubitBinaryOperator(Random random) {
    super(random);
  }

  public abstract Qubit evaluate(A first, B second);
}