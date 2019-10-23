package com.adamratzman.qiukit.operators.unary;


import com.adamratzman.qiukit.operators.QubitBinaryOperator;
import com.adamratzman.qiukit.qubit.Qubit;

import java.util.Random;

public class Write extends QubitBinaryOperator<Qubit, Qubit.State> {
  public Write(Random random) {
    super(random);
  }

  @Override
  public Qubit evaluate(Qubit qubit, Qubit.State qubitState) {
    Qubit readQubit = qubit.read();
    if ((qubitState == Qubit.State.ZERO && readQubit.isOne())
            || (qubitState == Qubit.State.ONE && readQubit.isZero())) {
      return readQubit.not();
    } else return readQubit;
  }
}
