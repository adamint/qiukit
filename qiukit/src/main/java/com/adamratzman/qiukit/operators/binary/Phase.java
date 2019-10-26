package com.adamratzman.qiukit.operators.binary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.QubitAmplitude;
import com.adamratzman.qiukit.operators.QubitBinaryOperator;
import org.apache.commons.math3.complex.ComplexUtils;

import java.util.Random;

public class Phase extends QubitBinaryOperator<Qubit, Double> {
  public Phase(Random random) {
    super("Phase", random);
  }

  @Override
  public Qubit evaluate(Qubit argument, Double radians) {
    double realAdded = ComplexUtils.polar2Complex(1, radians + argument.getOne().getAngle()).getReal();
    double scaleFactor;
    if (realAdded == 0) scaleFactor = 1;
    else scaleFactor =1.0 / realAdded;
    return new Qubit(argument.getZero(), new QubitAmplitude(Math.abs(argument.getOne().getCoefficient() * scaleFactor), argument.getOne().getAngle() + radians), getRandom());
  }
}
