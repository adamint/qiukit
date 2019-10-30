package com.adamratzman.qiukit.operators;

import javafx.util.Pair;

import java.util.List;
import java.util.Random;

public abstract class QubitBinaryOperator<A, B, C> extends QubitOperator<Pair<A, B>, C> {
  public QubitBinaryOperator(String name, Random random) {
    super(name, random);
  }

  public QubitBinaryOperator(String name) {
    this(name, new Random());
  }

  @Override
  public C apply(List<Pair<A, B>> arguments) {
    return evaluate(arguments.get(0).getKey(), arguments.get(0).getValue());
  }

  public abstract C evaluate(A first, B second);
}