package com.adamratzman.qiukit.utils;

import org.apache.commons.math3.complex.Complex;

import static com.adamratzman.qiukit.QubitAmplitude.delta;

public class MathUtils {
  public static boolean equals(double one, double two, double tolerance) {
    return Math.abs(one - two) < tolerance;
  }

  public static double getNorm(Complex complex) {
    return Math.sqrt(Math.pow(complex.getReal(), 2) + Math.pow(complex.getImaginary(),2));
  }

  public static Complex standardize(Complex complex) {
    return complex.multiply(getNorm(complex));
  }

  public static double normalize(double scalar, double against, double to) {
    if (equals(scalar, 0, delta)) return to;
    return scalar / against * to;
  }

  public static double fixedAtan2(double y, double x) {
    if (MathUtils.equals(y, 0, delta)) y = 0;
    if (MathUtils.equals(x, 0, delta)) x = 0;

    return Math.atan2(y, x);
  }
}
