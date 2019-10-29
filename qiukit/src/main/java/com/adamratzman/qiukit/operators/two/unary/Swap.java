package com.adamratzman.qiukit.operators.two.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;
import javafx.util.Pair;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

import java.util.Random;

public class Swap extends QubitUnaryOperator<Pair<Qubit, Qubit>, Pair<Qubit, Qubit>> {
  private FieldMatrix<Complex> swapMatrix = MatrixUtils.createFieldMatrix(
          new Complex[][]{
                  new Complex[]{new Complex(1), new Complex(0), new Complex(0), new Complex(0)},
                  new Complex[]{new Complex(0), new Complex(0), new Complex(1), new Complex(0)},
                  new Complex[]{new Complex(0), new Complex(1), new Complex(0), new Complex(0)},
                  new Complex[]{new Complex(0), new Complex(0), new Complex(0), new Complex(1)}
          }
  );

  public Swap(Random random) {
    super("Swap", random);
  }

  @Override
  public Pair<Qubit, Qubit> evaluate(Pair<Qubit, Qubit> argument) {
    FieldMatrix<Complex> result = swapMatrix.multiply(
            MatrixUtils.createFieldMatrix(
                    new Complex[][]{
                            new Complex[]{argument.getKey().getZero().getComplex(), argument.getValue().getZero().getComplex()},
                            new Complex[]{argument.getKey().getOne().getComplex(), argument.getValue().getOne().getComplex()}
                    }
            )
    );

    return null;

  }
}
