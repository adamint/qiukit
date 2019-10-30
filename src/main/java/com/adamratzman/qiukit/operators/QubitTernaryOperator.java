package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.utils.Triple;

import java.util.List;
import java.util.Random;

public abstract class QubitTernaryOperator<A,B,C, D> extends QubitOperator<Triple<A,B,C>, D> {
  public QubitTernaryOperator(String name, Random random) {
    super(name, random);
  }
  public QubitTernaryOperator(String name) {
    super(name, new Random());
  }

  @Override
  public D apply(List<Triple<A, B, C>> arguments) {
    return evaluate(arguments.get(0).getA(), arguments.get(0).getB(), arguments.get(0).getC());
  }

  public abstract D evaluate(A first, B second, C third);
}
