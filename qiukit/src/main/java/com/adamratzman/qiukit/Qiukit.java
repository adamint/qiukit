package com.adamratzman.qiukit;

import com.adamratzman.qiukit.library.QiukitLibrary;
import com.adamratzman.qiukit.operators.one.unary.Hadamard;

import java.util.Random;

public class Qiukit {
  private static Random random = new Random(1);
  public static void main(String[] args) {
    System.out.println(QiukitLibrary.hadRead(random));
    System.out.println(new Hadamard(random).evaluate(Qubit.getQubit(Qubit.State.ZERO, random)));
    Qubit zero = Qubit.getQubit(Qubit.State.ZERO);
    System.out.println(zero);
    System.out.println(zero.phase(Math.PI / 4));
    System.out.println(zero.phase(Math.PI / 2));
    System.out.println(zero.phase(Math.PI * 3 / 4));
    System.out.println(zero.phase(Math.PI));
  }
}
