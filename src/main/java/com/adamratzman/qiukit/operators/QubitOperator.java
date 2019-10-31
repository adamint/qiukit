package com.adamratzman.qiukit.operators;

import java.util.List;
import java.util.Random;

public abstract class QubitOperator<T, A> {
  private String name;
  private Random random;
  private String invocationDescription;

  public QubitOperator(String name, Random random, String invocationDescription) {
    this.name = name;
    this.random = random;
    this.invocationDescription = invocationDescription;
  }

  public Random getRandom() {
    return random;
  }

  public String getName() {
    return name;
  }

  public void setRandom(Random random) {
    this.random = random;
  }

  public abstract A apply(List<T> arguments);

  @Override
  public String toString() {
    return "QubitOperator(" + name + ")";
  }

  public String getInvocationDescription() {
    return invocationDescription;
  }
}
