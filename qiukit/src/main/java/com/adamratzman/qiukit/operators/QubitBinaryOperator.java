package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.Qubit;

import java.util.Random;

abstract class QubitBinaryOperator<A, B> extends QubitOperator {
  public QubitBinaryOperator(Random random) {
    super(random);
  }

  abstract Qubit evaluate(A first, B second);
}