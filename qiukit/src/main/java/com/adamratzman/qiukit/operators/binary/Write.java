package com.adamratzman.qiukit.operators.binary;


import com.adamratzman.qiukit.operators.QubitBinaryOperator;
import com.adamratzman.qiukit.Qubit;

import java.util.Random;

public class Write extends QubitBinaryOperator<Qubit, Qubit.State> {
  public Write(Random random) {
    super("Write", random);
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
