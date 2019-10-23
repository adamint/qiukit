package com.adamratzman.qiukit;

import org.apache.commons.math3.complex.Complex;

public class QubitVector {
  private Complex vector;

  public QubitVector(Complex vector) {
    this.vector = vector;
  }

  public Complex getVector() {
    return vector;
  }

  public void setVector(Complex vector) {
    this.vector = vector;
  }

  public double getProbability() {
    return Math.pow(getMagnitude(), 2.0);
  }

  public double getMagnitude() {
    return Math.sqrt(Math.pow(vector.getReal(), 2.0) + Math.pow(vector.getImaginary(), 2.0));
  }
}
