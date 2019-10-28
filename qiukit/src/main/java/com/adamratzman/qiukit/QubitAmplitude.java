package com.adamratzman.qiukit;

import com.adamratzman.qiukit.utils.MathUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

public class QubitAmplitude {
  public static double delta = 0.00001;

  private double sqrtProbability;
  private double theta;

  public QubitAmplitude(double sqrtProbability, double theta) {
    this.sqrtProbability = sqrtProbability;
    this.theta = theta;
    while (this.theta > 2 * Math.PI || MathUtils.equals(this.theta, 2 * Math.PI, delta)) this.theta -= 2 * Math.PI;
    while (this.theta < 0) this.theta += 2 * Math.PI;
  }

  public QubitAmplitude(double sqrtProbability) {
    this.sqrtProbability = sqrtProbability;
    this.theta = 0;
  }

  public QubitAmplitude(Complex complex) {
    this(Math.sqrt(Math.pow(complex.getReal(), 2) + Math.pow(complex.getImaginary(), 2)), Math.atan2(complex.getImaginary(), complex.getReal()));
  }

  public Complex getComplex() {
    return new Complex(Math.cos(theta), Math.sin(theta)).multiply(sqrtProbability);
  }

  public double getSqrtProbability() {
    return sqrtProbability;
  }

  public double getAngle() {
    while (theta < 0) theta += 2 * Math.PI;
    return theta;
  }

  public QubitAmplitude plus(QubitAmplitude other) {
    return new QubitAmplitude(sqrtProbability * other.sqrtProbability, getAngle() + other.getAngle());
  }

  public QubitAmplitude plusTheta(double otherTheta) {
    Complex complexTheta =ComplexUtils.polar2Complex(1, otherTheta);
    return new QubitAmplitude(getComplex().multiply(complexTheta));
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof QubitAmplitude) {
      QubitAmplitude otherAmplitude = (QubitAmplitude) o;
      return MathUtils.equals(sqrtProbability, otherAmplitude.getSqrtProbability(), delta)
              && MathUtils.equals(getAngle(), otherAmplitude.getAngle(), delta);
    } else return false;
  }

  @Override
  public String toString() {
    return "QubitAmplitude(prob=" + getSqrtProbability() + ", phase=" + getAngle() + ")";
  }
}
