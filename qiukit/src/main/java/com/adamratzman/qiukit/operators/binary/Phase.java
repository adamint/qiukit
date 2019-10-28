package com.adamratzman.qiukit.operators.binary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.QubitAmplitude;
import com.adamratzman.qiukit.operators.QubitBinaryOperator;

import java.util.Random;

public class Phase extends QubitBinaryOperator<Qubit, Double> {
  public Phase(Random random) {
    super("Phase", random);
  }

  @Override
  public Qubit evaluate(Qubit argument, Double radians) {
    QubitAmplitude newOneAmplitude = argument.getOne().plusTheta(radians);
    return new Qubit(argument.getZero(), newOneAmplitude, getRandom());
  }
}
