package com.adamratzman.qiukit.operators;

import java.util.Random;

public abstract class QubitOperator {
  private Random random;

  public QubitOperator(Random random) {
    this.random = random;
  }

  public Random getRandom() {
    return random;
  }

  public void setRandom(Random random) {
    this.random = random;
  }
}
