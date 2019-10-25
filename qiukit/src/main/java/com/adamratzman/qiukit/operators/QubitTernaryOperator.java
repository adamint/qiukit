package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.utils.Triple;

import java.util.List;
import java.util.Random;

public abstract class QubitTernaryOperator<A,B,C> extends QubitOperator<Triple<A,B,C>, Qubit> {
  public QubitTernaryOperator(String name, Random random) {
    super(name, random);
  }

  @Override
  public Qubit apply(List<Triple<A, B, C>> arguments) {
    return evaluate(arguments.get(0).getA(), arguments.get(0).getB(), arguments.get(0).getC());
  }

  public abstract Qubit evaluate(A first, B second, C third);
}
