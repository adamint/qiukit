package com.adamratzman.qiukit.operators.one.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

import java.util.Random;

public class Hadamard extends QubitUnaryOperator<Qubit, Qubit> {
  private FieldMatrix<Complex> hadamardMatrix = MatrixUtils.createFieldMatrix(
          new Complex[][]{
                  new Complex[]{new Complex(1), new Complex(1)},
                  new Complex[]{new Complex(1), new Complex(-1)}
          }
  );

  public Hadamard(Random random) {
    super("Hadamard", random);
  }

  public Hadamard() {
    super("Hadamard");
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    return new Qubit(
            argument.getZero().getComplex().add(argument.getOne().getComplex()).multiply(1 / Math.sqrt(2)),
            argument.getZero().getComplex().subtract(argument.getOne().getComplex()).multiply(1 / Math.sqrt(2)),
            argument.getRandom()
    );
  }
}
