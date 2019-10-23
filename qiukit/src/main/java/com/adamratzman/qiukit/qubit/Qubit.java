package com.adamratzman.qiukit.qubit;

import com.adamratzman.qiukit.operators.unary.Hadamard;
import com.adamratzman.qiukit.operators.unary.Not;
import com.adamratzman.qiukit.operators.unary.Read;
import com.adamratzman.qiukit.operators.unary.Write;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class Qubit {
  private Complex complexRepresentation;
  private Random random;

  private Read read;
  private Hadamard hadamard;
  private Not not;
  private Write write;

  public Qubit(Complex complexRepresentation, Random random) {
    this.complexRepresentation = complexRepresentation;
    this.random = random;

    this.read = new Read(random);
    this.hadamard = new Hadamard(random);
    this.not = new Not(random);
    this.write = new Write(random);
  }

  public Qubit(double real, double imaginary, Random random) {
    this(new Complex(real, imaginary), random);
  }

  public Qubit(double[][] matrix, Random random) {
    this(matrix[0][0], matrix[1][0], random);
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

  public Complex getComplexRepresentation() {
    return complexRepresentation;
  }

  public Random getRandom() {
    return random;
  }

  public double[] getAsVector() {
    return new double[]{getZero(), getOne()};
  }

  public double[][] getAsMatrix() {
    double[] vector = getAsVector();
    return new double[][]{new double[]{vector[0]}, new double[]{vector[1]}};
  }

  public boolean is(State state) {
    return getProbability(state) == 1.0;
  }

  public double getZero() {
    return complexRepresentation.getReal();
  }

  public double getOne() {
    return complexRepresentation.getImaginary();
  }

  public double getProbability(State state) {
    double coefficient;
    if (state == State.ZERO) coefficient = getZero();
    else coefficient = getOne();
    return Math.pow(coefficient, 2.0);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Qubit(").append(getZero()).append(", ").append(getOne()).append(")");
    return sb.toString();
  }

  public static Qubit getQubit(State state) {
    return getQubit(state, new Random());
  }

  public static Qubit getQubit(State state, Random random) {
    if (state == State.ZERO) return new Qubit(
            new Complex(1.0),
            random);
    else if (state == State.ONE) return new Qubit(
            new Complex(0, 1.0),
            random);
    else throw new IllegalArgumentException();
  }

  public enum State {
    ONE, ZERO
  }
}
