package com.adamratzman.qiukit.operators.binary;

import com.adamratzman.qiukit.operators.QubitBinaryOperator;
import com.adamratzman.qiukit.Qubit;

import java.util.Random;

public class Phase extends QubitBinaryOperator<Qubit, Double> {
  public Phase(Random random) {
    super("Phase", random);
  }

  @Override
  public Qubit evaluate(Qubit argument, Double radians) {
    return argument.addRelativePhase(radians);
  }
}
