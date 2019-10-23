package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.operators.QubitUnaryOperator;
import com.adamratzman.qiukit.qubit.Qubit;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Random;

public class Hadamard extends QubitUnaryOperator<Qubit> {
  private RealMatrix hadamardMatrix = MatrixUtils.createRealMatrix(
          new double[][]{
                  new double[]{1, 1},
                  new double[]{1, -1}
          }
  );

  public Hadamard(Random random) {
    super("Hadamard", random);
  }

  @Override
  public Qubit evaluate(Qubit argument) {
    RealMatrix result = hadamardMatrix
            .multiply(MatrixUtils.createRealMatrix(argument.getAsMatrix()))
            .scalarMultiply(1.0 / Math.sqrt(2));
    return new Qubit(result.getData(), argument.getRandom());
  }
}
