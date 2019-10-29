package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.one.unary.*;
import com.adamratzman.qiukit.operators.one.binary.*;
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
  private PauliY pauliY;
  private PauliZ pauliZ;
  private SPhase sPhase;
  private TPhase tPhase;

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
    this.pauliY = new PauliY(random);
    this.pauliZ = new PauliZ(random);
    this.tPhase = new TPhase(random);
    this.sPhase = new SPhase(random);
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

  public static Qubit getPlusQubit(Random random) {
    return getQubit(State.ZERO, random).hadamard();
  }

  public static Qubit getPlusQubit() {
    return getPlusQubit(new Random());
  }

  public static Qubit getMinusQubit(Random random) {
    return getQubit(State.ONE, random).hadamard();
  }

  public static Qubit getMinusQubit() {
    return getMinusQubit(new Random());
  }

  public static Qubit getPlusYQubit(Random random) {
    return getQubit(State.ZERO, random).hadamard().phaseDegrees(270);
  }

  public static Qubit getPlusYQubit() {
    return getPlusYQubit(new Random());
  }

  public static Qubit getMinusYQubit(Random random) {
    return getQubit(State.ZERO, random).hadamard().phaseDegrees(90);
  }

  public static Qubit getMinusYQubit() {
    return getMinusYQubit(new Random());
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

  public Qubit pauliY() {
    return pauliY.evaluate(this);
  }

  public Qubit pauliZ() {
    return pauliZ.evaluate(this);
  }

  public Qubit sPhase() {
    return sPhase.evaluate(this);
  }

  public Qubit tPhase() {
    return tPhase.evaluate(this);
  }


  public double getRelativePhase() {
    double phase = oneAmplitude.getAngle() - zeroAmplitude.getAngle();
    while (phase >= 2 * Math.PI || MathUtils.equals(phase, 2 * Math.PI, delta)) phase -= 2 * Math.PI;
    while (phase < 0) phase += 2 * Math.PI;
    if (MathUtils.equals(phase, 0, delta)) return 0;

    return phase;
  }

  public Qubit phaseZero(double radians) {
    return new Qubit(getZero().plusTheta(radians), getOne(), random);
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

  public State getBaseState() {
    if (equals(getQubit(State.ZERO))) return State.ZERO;
    else if (equals(getQubit(State.ONE))) return State.ONE;
    else return null;
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
