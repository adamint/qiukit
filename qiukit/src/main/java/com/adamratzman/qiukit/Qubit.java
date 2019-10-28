package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.binary.Phase;
import com.adamratzman.qiukit.operators.binary.Write;
import com.adamratzman.qiukit.operators.unary.Hadamard;
import com.adamratzman.qiukit.operators.unary.Not;
import com.adamratzman.qiukit.operators.unary.Read;
import com.adamratzman.qiukit.operators.unary.RootOfNot;
import com.adamratzman.qiukit.utils.MathUtils;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

import static com.adamratzman.qiukit.QubitAmplitude.delta;

public class Qubit {
  private QubitAmplitude zeroAmplitude;
  private QubitAmplitude oneAmplitude;

  private Random random;

  private Read read;
  private Hadamard hadamard;
  private Not not;
  private Write write;
  private Phase phase;
  private RootOfNot rootOfNot;

  public Qubit(QubitAmplitude zeroAmplitude, QubitAmplitude oneAmplitude, Random random) {
    this.zeroAmplitude = zeroAmplitude;
    this.oneAmplitude = oneAmplitude;
    this.random = random;

    this.read = new Read(random);
    this.hadamard = new Hadamard(random);
    this.not = new Not(random);
    this.write = new Write(random);
    this.phase = new Phase(random);
    this.rootOfNot = new RootOfNot(random);
  }

  public Qubit(Complex zero, Complex one, Random random) {
    this(new QubitAmplitude(zero), new QubitAmplitude(one), random);
  }

  public Qubit(double realZero, double realOne, Random random) {
    this(new Complex(realZero), new Complex(realOne), random);
  }


  public Qubit(Complex[][] matrix, Random random) {
    this( new QubitAmplitude(matrix[0][0]),  new QubitAmplitude(matrix[1][0]), random);
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

  public Qubit rootOfNot() {
    return rootOfNot.evaluate(this);
  }

  public Qubit write(Qubit.State state) {
    return write.evaluate(this, state);
  }

  public double getRelativePhase() {
    double phase = oneAmplitude.getAngle() - zeroAmplitude.getAngle();
    while (phase >= 2 * Math.PI || MathUtils.equals(phase, 2 * Math.PI, delta)) phase -= 2 * Math.PI;
    while (phase < 0) phase += 2 * Math.PI;
    if (MathUtils.equals(phase, 0, delta)) return 0;

    return phase;
  }

  public Qubit phase(double radians) {
    return phase.evaluate(this, radians);
  }

  public Qubit phaseDegrees(double degrees) {
    return phase.evaluate(this, degrees * Math.PI / 180);
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
    if (state == State.ZERO) coefficient = getZero().getSqrtProbability();
    else coefficient = getOne().getSqrtProbability();
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
