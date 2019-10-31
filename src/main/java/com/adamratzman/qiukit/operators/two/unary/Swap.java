package com.adamratzman.qiukit.operators.two.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.QubitUnaryOperator;
import com.adamratzman.qiukit.utils.Pair;
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
    super("Swap", random, "firstQubit, secondQubit");
  }

  public Swap() {
    this(new Random());
  }

  @Override
  public Pair<Qubit, Qubit> evaluate(Pair<Qubit, Qubit> argument) {
    FieldMatrix<Complex> result = swapMatrix.multiply(
            MatrixUtils.createFieldMatrix(
                    new Complex[][]{
                            new Complex[]{argument.getKey().getZero().getComplex()},
                            new Complex[]{argument.getKey().getOne().getComplex()},
                            new Complex[]{argument.getValue().getZero().getComplex()},
                            new Complex[]{argument.getValue().getOne().getComplex()}
                    }
            )
    );

    Qubit one = new Qubit(result.getData()[1][0], result.getData()[3][0], argument.getKey().getRandom());
    Qubit two = new Qubit(result.getData()[0][0], result.getData()[2][0], argument.getValue().getRandom());

    return new Pair<>(one, two);
  }
}
