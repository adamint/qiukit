package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.unary.Hadamard;

import java.util.Random;

public class Qiukit {
  private static Random random = new Random(1);
  public static void main(String[] args) {
    new Hadamard(random).evaluate(Qubit.getOne(random));
  }
}
