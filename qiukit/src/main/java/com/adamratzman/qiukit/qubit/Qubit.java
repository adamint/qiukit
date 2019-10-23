package com.adamratzman.qiukit.qubit;

import com.adamratzman.qiukit.operators.unary.Hadamard;
import com.adamratzman.qiukit.operators.unary.Not;
import com.adamratzman.qiukit.operators.unary.Read;
import com.adamratzman.qiukit.operators.unary.Write;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class Qubit {
  private QubitVector zeroVector;
  private QubitVector oneVector;
  private Random random;

  private Read read;
  private Hadamard hadamard;
  private Not not;
  private Write write;

  public Qubit(QubitVector zeroVector, QubitVector oneVector, Random random) {
    this.zeroVector = zeroVector;
    this.oneVector = oneVector;
    this.random = random;

    this.read = new Read(random);
    this.hadamard = new Hadamard(random);
    this.not = new Not(random);
    this.write = new Write(random);
  }

  public Qubit read() {
    return read.evaluate(this);
  }

  public Qubit hadamard() {
    return hadamard.evaluate(this);
  }

  public Qubit not() {
    return not.evaluate(this);
  }

  public Qubit write(Qubit.State state) {
    return write.evaluate(this, state);
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

  public boolean isZero() {
    return getZeroVector().getProbability() == 1.0;
  }

  public boolean isOne() {
    return getOneVector().getProbability() == 1.0;
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

  public enum State {
    ONE, ZERO
  }
}
