package com.adamratzman.qiukit.utils;

import org.apache.commons.math3.complex.Complex;

public class MathUtils {
  public static boolean equals(double one, double two, double tolerance) {
    return Math.abs(one - two) < tolerance;
  }

  public static double getNorm(Complex complex) {
    return Math.sqrt(Math.pow(complex.getReal(), 2) + Math.pow(complex.getImaginary(),2));
  }
}
