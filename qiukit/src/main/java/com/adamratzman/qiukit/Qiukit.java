package com.adamratzman.qiukit;

import com.adamratzman.qiukit.library.QiukitLibrary;
import com.adamratzman.qiukit.operators.unary.Hadamard;
import com.adamratzman.qiukit.qubit.Qubit;

import java.util.Random;

public class Qiukit {
  private static Random random = new Random(1);
  public static void main(String[] args) {
    System.out.println(QiukitLibrary.generateRandomQubit(random));
    System.out.println(new Hadamard(random).evaluate(Qubit.getQubit(Qubit.State.ZERO, random)));
  }
}
