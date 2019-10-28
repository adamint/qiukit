package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class PauliY extends QubitUnaryOperator<Qubit> {
  public PauliY(Random random) {
    super("Y", random);
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return new Qubit(
            argument.getOne().getComplex().multiply(new Complex(0, -1)),
            argument.getZero().getComplex().multiply(new Complex(0, 1)),
            argument.getRandom()
    );
  }
}
