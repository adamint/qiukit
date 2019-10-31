package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;
import org.apache.commons.math3.complex.Complex;

import java.util.Random;

public class PauliY extends QubitUnaryOperator<Qubit, Qubit> {
  public PauliY(Random random) {
    super("Y", random, "qubit");
  }

  public PauliY() {
    this(new Random());
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
