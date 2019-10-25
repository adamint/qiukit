package com.adamratzman.qiukit.utils;

public class MathUtils {
  public static boolean equals(double one, double two, double tolerance) {
    return Math.abs(one - two) < tolerance;
  }
}
