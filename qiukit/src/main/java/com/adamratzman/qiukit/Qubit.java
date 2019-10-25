package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.binary.Phase;
import com.adamratzman.qiukit.operators.binary.Write;
import com.adamratzman.qiukit.operators.unary.*;
import com.adamratzman.qiukit.utils.MathUtils;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class Qubit {
  private Complex complexRepresentation;
  private double onePhase = 0;
  private double zeroPhase = 0;

  private Random random;

  private Read read;
  private Hadamard hadamard;
  private Not not;
  private Write write;
  private Phase phase;

  private static double delta = 0.00001;

  public Qubit(Complex complexRepresentation, Random random) {
    this.complexRepresentation = complexRepresentation;
    this.random = random;

    this.read = new Read(random);
    this.hadamard = new Hadamard(random);
    this.not = new Not(random);
    this.write = new Write(random);
    this.phase = new Phase(random);
  }

  public Qubit(Complex complexRepresentation, double zeroPhase, double onePhase, Random random) {
    this(complexRepresentation, random);
    this.onePhase = onePhase;
    this.zeroPhase = zeroPhase;
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

  public Qubit phase(double radians) {
    return phase.evaluate(this, radians);
  }

  public Qubit setPhase(State state, double phase) {
    if (state == State.ZERO) return new Qubit(complexRepresentation, phase, onePhase, random);
    else return new Qubit(complexRepresentation, zeroPhase, phase, random);
  }

  public Qubit addPhase(State state, double phaseToAdd) {
    if (state == State.ZERO) return setPhase(State.ZERO, zeroPhase + phaseToAdd);
    else return setPhase(State.ONE, onePhase + phaseToAdd);
  }

  public Qubit addRelativePhase(double phaseToAdd) {
    return addPhase(State.ONE, phaseToAdd);
  }

  public Qubit subtractRelativePhase(double phaseToRemove) {
    return addRelativePhase(-phaseToRemove);
  }

  public double getRelativePhase() {
    return onePhase - zeroPhase;
  }

  public double getPhase(State state) {
    if (state == State.ZERO) return zeroPhase;
    else return onePhase;
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

  public Complex[][] getAsComplexMatrix() {
    double[] realVector = getAsVector();
    return new Complex[][]{new Complex[]{new Complex(realVector[0])}, new Complex[]{new Complex(realVector[1])}};
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
    sb.append("Qubit(zero=" + getZero() + ", one=" + getOne() + ", phaseZero=" + getPhase(State.ZERO) + ", phaseOne=" + getPhase(State.ONE) + ")");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Qubit) {
      Qubit otherQubit = (Qubit) o;
      return MathUtils.equals(getZero(), otherQubit.getZero(), delta)
              && MathUtils.equals(getOne(), otherQubit.getOne(), delta);
    } else return false;
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
