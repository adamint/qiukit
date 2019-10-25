package com.adamratzman.qiukit;

import com.adamratzman.qiukit.utils.MathUtils;
import org.apache.commons.math3.complex.Complex;

public class QubitAmplitude {
  private static double delta = 0.00001;

  private Complex amplitude;

  public QubitAmplitude(Complex amplitude) {
    if (amplitude.getReal() >= 0) this.amplitude = new Complex(amplitude.getReal(), amplitude.getImaginary());
    else
      this.amplitude = new Complex(Math.abs(amplitude.getReal()), amplitude.getImaginary() + (Math.tan(Math.PI / 2) * amplitude.getReal()));
  }

  public QubitAmplitude(double real, double imaginary) {
    this(new Complex(real, imaginary));
  }

  public Complex getComplex() {
    return amplitude;
  }

  public double getReal() {
    return amplitude.getReal();
  }

  public double getImaginary() {
    return amplitude.getImaginary();
  }

  public double getAngle() {
    return Math.atan2(Math.abs(amplitude.getImaginary()), Math.abs(amplitude.getReal()));
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
    return "QubitAmplitude(real=" + getReal() + ", imaginary=" + getImaginary() + ", phase=" + getAngle() + ")";
  }
}
