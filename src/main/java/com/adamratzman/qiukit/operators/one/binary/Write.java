package com.adamratzman.qiukit.operators.one.binary;


import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitBinaryOperator;

import java.util.Random;

public class Write extends QubitBinaryOperator<Qubit, Qubit.State, Qubit> {
  public Write(Random random) {
    super("Write", random, "(zero or one)");
  }

  public Write() {
    this(new Random());
  }

  @Override
  public Qubit evaluate(Qubit qubit, Qubit.State qubitState) {
    Qubit readQubit = qubit.read();
    if ((qubitState == Qubit.State.ZERO && readQubit.is(Qubit.State.ONE))
            || (qubitState == Qubit.State.ONE && readQubit.is(Qubit.State.ZERO))) {
      return readQubit.not();
    } else return readQubit;
  }
}
