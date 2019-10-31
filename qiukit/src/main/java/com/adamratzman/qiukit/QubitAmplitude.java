package com.adamratzman.qiukit;

import com.adamratzman.qiukit.utils.MathUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

public class QubitAmplitude {
  public static double delta = 0.00001;

  private double sqrtProbability;
  private double theta;

  public QubitAmplitude(double sqrtProbability, double theta) {
    if (sqrtProbability >= 0) {
      this.sqrtProbability = sqrtProbability;
      this.theta = theta;
    }
    else {
      this.sqrtProbability = -sqrtProbability;
      this.theta = theta + Math.PI;
    }

    while (this.theta > Math.PI && !MathUtils.equals(this.theta, Math.PI, delta)) this.theta -= 2 * Math.PI;
    while (this.theta < -Math.PI && !MathUtils.equals(this.theta, -Math.PI, delta)) this.theta += 2 * Math.PI;
  }

  public QubitAmplitude(double sqrtProbability) {
    this(sqrtProbability, 0);
  }

  public QubitAmplitude(Complex complex) {
    this(Math.sqrt(Math.pow(complex.getReal(), 2.0) + Math.pow(complex.getImaginary(), 2.0)), MathUtils.fixedAtan2(complex.getImaginary(), complex.getReal()));
  }

  public Complex getComplex() {
    return new Complex( Math.cos(theta), Math.sin(theta)).multiply(sqrtProbability);
  }

  public double getSqrtProbability() {
    return sqrtProbability;
  }

  public double getAngle() {
    while (theta < 0 && !MathUtils.equals(theta, 0, delta)) theta += 2 * Math.PI;
    return theta;
  }

  public QubitAmplitude plus(QubitAmplitude other) {
    return new QubitAmplitude(sqrtProbability * other.sqrtProbability, getAngle() + other.getAngle());
  }

  public QubitAmplitude plusTheta(double otherTheta) {
    return new QubitAmplitude(sqrtProbability, theta + otherTheta);
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
    return "QubitAmplitude(prob=" + getSqrtProbability() + ", phase=" + getAngle() + ", complex=" + getComplex() + ")";
  }
}
