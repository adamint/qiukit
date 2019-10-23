package com.adamratzman.qiukit;

import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class Qubit {
  private QubitVector zeroVector;
  private QubitVector oneVector;
  private Random random;

  public Qubit(QubitVector zeroVector, QubitVector oneVector, Random random) {
    this.zeroVector = zeroVector;
    this.oneVector = oneVector;
    this.random = random;
  }

  public QubitVector getOneVector() {
    return oneVector;
  }

  public void setOneVector(QubitVector oneVector) {
    this.oneVector = oneVector;
  }

  public QubitVector getZeroVector() {
    return zeroVector;
  }

  public void setZeroVector(QubitVector zeroVector) {
    this.zeroVector = zeroVector;
  }


  public Random getRandom() {
    return random;
  }

  public double[] getAsVector() {
    return new double[]{zeroVector.getMagnitude(), oneVector.getMagnitude()};
  }

  public double[][] getAsMatrix() {
    double[] vector = getAsVector();
    return new double[][]{new double[]{vector[0]}, new double[]{vector[1]}};
  }

  public static Qubit getZero(Random random) {
    return new Qubit(
            new QubitVector(new Complex(1.0)),
            new QubitVector(new Complex(0.0)),
            random);
  }

  public static Qubit getOne(Random random) {
    return new Qubit(
            new QubitVector(new Complex(0.0)),
            new QubitVector(new Complex(1.0)),
            random);
  }
}
