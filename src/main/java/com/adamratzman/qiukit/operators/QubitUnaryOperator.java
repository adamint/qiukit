package com.adamratzman.qiukit.operators;

import java.util.List;
import java.util.Random;

public abstract class QubitUnaryOperator<T, C> extends QubitOperator<T, C> {
  public QubitUnaryOperator(String name, Random random, String invocationDescription) {
    super(name, random, "");
  }

  public QubitUnaryOperator(String name, String invocationDescription) {
    super(name, new Random(), "");
  }

  @Override
  public C apply(List<T> arguments) {
    return evaluate(arguments.get(0));
  }

  public abstract C evaluate(T argument);

}
