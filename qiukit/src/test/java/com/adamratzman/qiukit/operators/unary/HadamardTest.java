package com.adamratzman.qiukit.operators.unary;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.QubitAmplitude;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class HadamardTest {
  private Qubit zero = Qubit.getQubit(Qubit.State.ZERO);
  private Qubit one = Qubit.getQubit(Qubit.State.ONE);

  private Random random = new Random();

  @Test
  public void evaluate() {
    Assert.assertEquals(zero, zero.hadamard().hadamard());
    Assert.assertEquals(
            new Qubit(1 / Math.sqrt(2), 1 / Math.sqrt(2), random),
            zero.hadamard()
    );

    Qubit qubit = new Qubit(Math.sqrt(0.854), Math.sqrt(.146), random);
    Assert.assertNotEquals(qubit, qubit.hadamard());
    System.out.println(qubit);
    Assert.assertEquals(qubit, qubit.hadamard().hadamard());

    System.out.println(one.hadamard());

    new Qubit(new QubitAmplitude())
  }
}
