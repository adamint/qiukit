package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.binary.Phase;
import com.adamratzman.qiukit.operators.binary.Write;
import com.adamratzman.qiukit.operators.unary.Hadamard;
import com.adamratzman.qiukit.operators.unary.Not;
import com.adamratzman.qiukit.operators.unary.Read;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class Qubit {
  private QubitAmplitude zeroAmplitude;
  private QubitAmplitude oneAmplitude;

  private Random random;

  private Read read;
  private Hadamard hadamard;
  private Not not;
  private Write write;
  private Phase phase;

  public Qubit(QubitAmplitude zeroAmplitude, QubitAmplitude oneAmplitude, Random random) {
    this.zeroAmplitude = zeroAmplitude;
    this.oneAmplitude = oneAmplitude;
    this.random = random;

    this.read = new Read(random);
    this.hadamard = new Hadamard(random);
    this.not = new Not(random);
    this.write = new Write(random);
    this.phase = new Phase(random);
  }

  public Qubit(Complex zero, Complex one, Random random) {
    this(new QubitAmplitude(zero), new QubitAmplitude(one), random);
  }

  public Qubit(double realZero, double realOne, Random random) {
    this(new Complex(realZero), new Complex(realOne), random);
  }


  public Qubit(Complex[][] matrix, Random random) {
    this(new QubitAmplitude(matrix[0][0]), new QubitAmplitude(matrix[1][0]), random);
  }

  public static Qubit getQubit(State state) {
    return getQubit(state, new Random());
  }

  public static Qubit getQubit(State state, Random random) {
    if (state == State.ZERO) return new Qubit(
            new Complex(1.0),
            new Complex(0),
            random);
    else if (state == State.ONE) return new Qubit(
            new Complex(0),
            new Complex(1.0),
            random);
    else throw new IllegalArgumentException();
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

  public Qubit setPhase(State state, double phase) {
    if (state == State.ZERO)
      return new Qubit(new QubitAmplitude(zeroAmplitude.getReal(), Math.tan(phase) * zeroAmplitude.getReal()), oneAmplitude, random);
    else
      return new Qubit(zeroAmplitude, new QubitAmplitude(oneAmplitude.getReal(), Math.tan(phase) * zeroAmplitude.getReal()), random);
  }

  public Qubit addPhase(State state, double phaseToAdd) {
    if (state == State.ZERO) return setPhase(State.ZERO, zeroAmplitude.getAngle() + phaseToAdd);
    else return setPhase(State.ONE, oneAmplitude.getAngle() + phaseToAdd);
  }

  public Qubit addRelativePhase(double phaseToAdd) {
    return addPhase(State.ONE, phaseToAdd);
  }

  public Qubit subtractRelativePhase(double phaseToRemove) {
    return addRelativePhase(-phaseToRemove);
  }

  public double getPhase(State state) {
    if (state == State.ZERO) return zeroAmplitude.getAngle();
    else return oneAmplitude.getAngle();
  }

  public Qubit write(Qubit.State state) {
    return write.evaluate(this, state);
  }

  public double getRelativePhase() {
    return oneAmplitude.getAngle() - zeroAmplitude.getAngle();
  }

  public Qubit phase(double radians) {
    return phase.evaluate(this, radians);
  }

  public Random getRandom() {
    return random;
  }

  public QubitAmplitude[] getAsVector() {
    return new QubitAmplitude[]{getZero(), getOne()};
  }

  public QubitAmplitude[][] getAsMatrix() {
    QubitAmplitude[] vector = getAsVector();
    return new QubitAmplitude[][]{new QubitAmplitude[]{vector[0]}, new QubitAmplitude[]{vector[1]}};
  }

  public Complex[][] getAsComplexMatrix() {
    QubitAmplitude[] realVector = getAsVector();
    return new Complex[][]{new Complex[]{realVector[0].getComplex()}, new Complex[]{realVector[1].getComplex()}};
  }

  public boolean is(State state) {
    return getProbability(state) == 1.0;
  }

  public QubitAmplitude getZero() {
    return zeroAmplitude;
  }

  public QubitAmplitude getOne() {
    return oneAmplitude;
  }

  public double getProbability(State state) {
    double coefficient;
    if (state == State.ZERO) coefficient = getZero().getReal();
    else coefficient = getOne().getReal();
    return Math.pow(coefficient, 2.0);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Qubit(zero=" + getZero() + ", one=" + getOne() + ", relativePhase=" + getRelativePhase() + ")");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Qubit) {
      Qubit otherQubit = (Qubit) o;
      return getZero().equals(otherQubit.getZero()) && getOne().equals(otherQubit.getOne());
    } else return false;
  }

  public enum State {
    ONE, ZERO
  }
}
