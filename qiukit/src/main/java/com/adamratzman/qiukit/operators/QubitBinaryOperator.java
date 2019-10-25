package com.adamratzman.qiukit.operators;

import com.adamratzman.qiukit.Qubit;
import javafx.util.Pair;

import java.util.List;
import java.util.Random;

public abstract class QubitBinaryOperator<A, B> extends QubitOperator<Pair<A, B>, Qubit> {
  public QubitBinaryOperator(String name, Random random) {
    super(name, random);
  }

  @Override
  public Qubit apply(List<Pair<A, B>> arguments) {
    return evaluate(arguments.get(0).getKey(), arguments.get(0).getValue());
  }

  public abstract Qubit evaluate(A first, B second);
}