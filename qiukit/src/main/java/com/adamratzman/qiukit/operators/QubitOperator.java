package com.adamratzman.qiukit.operators;

import java.util.List;
import java.util.Random;

public abstract class QubitOperator<T, A> {
  private String name;
  private Random random;

  public QubitOperator(String name, Random random) {
    this.name = name;
    this.random = random;
  }

  public Random getRandom() {
    return random;
  }

  public void setRandom(Random random) {
    this.random = random;
  }

  public abstract A apply(List<T> arguments);

  @Override
  public String toString() {
    return "QubitOperator(" + name + ")";
  }
}
