package com.adamratzman.qiukit;

import com.adamratzman.qiukit.utils.MathUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

public class QubitAmplitude {
  public static double delta = 0.00001;

  private double r;
  private double theta;

  public QubitAmplitude(double r, double theta) {
    this.r = r;
    this.theta = theta;
    while (this.theta >= Math.PI) this.theta -= Math.PI;
  }

  public Complex getComplex() {
    System.out.println("r " + r + " | theta " + theta + " | real " + (r * Math.cos(theta)));
    Complex asComplex = ComplexUtils.polar2Complex(r, theta);
    return new Complex(asComplex.getReal(), asComplex.getImaginary());
  }

  public double getReal() {
    return getComplex().getReal();
  }

  public double getImaginary() {
    return getComplex().getImaginary();
  }

  public double getAngle() {
    return theta;
  }

  public double getCoefficient() {
    return r;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof QubitAmplitude) {
      QubitAmplitude otherAmplitude = (QubitAmplitude) o;
      return MathUtils.equals(getReal(), otherAmplitude.getReal(), delta)
              && MathUtils.equals(getImaginary(), otherAmplitude.getImaginary(), delta);
    } else return false;
  }

  @Override
  public String toString() {
    return "QubitAmplitude(real=" + getReal() + ", imaginary=" + getImaginary() + ", complex=" + getComplex() + ", phase=" + getAngle() + ")";
  }

  public static QubitAmplitude fromComplex(Complex complex) {
    if (complex.getReal() < 0) {
      return new QubitAmplitude(Math.sqrt(Math.pow(complex.getReal(), 2) + Math.pow(complex.getImaginary(), 2)), Math.PI / 2 + Math.atan2(complex.getImaginary(), Math.abs(complex.getReal())));
    }
    return new QubitAmplitude(Math.sqrt(Math.pow(complex.getReal(), 2) + Math.pow(complex.getImaginary(), 2)), Math.atan2(complex.getImaginary(), Math.abs(complex.getReal())));
  }
}
