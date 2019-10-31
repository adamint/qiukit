package com.adamratzman.qiukit.samples;

import com.adamratzman.qiukit.Circuit;
import com.adamratzman.qiukit.ComputationalResult;
import com.adamratzman.qiukit.Gate;
import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.one.unary.Hadamard;
import com.adamratzman.qiukit.operators.one.unary.Read;

import java.util.List;
import java.util.stream.Collectors;

public class RandomBit {
  public static void main(String[] args) {
    Qubit zero = Qubit.getQubit(Qubit.State.ZERO);

    Circuit randomBitCircuit = new Circuit(
            new Gate<Qubit, Qubit>(new Hadamard()),
            new Gate<Qubit, Qubit>(new Read())
    );

    List<ComputationalResult> resultProgression = randomBitCircuit.evaluate(zero);

    Qubit result = (Qubit) resultProgression.get(resultProgression.size() - 1).getAfter();
    System.out.println("\nResulted in: " + result.getState());

    System.out.println("\nCircuit evaluation progression:\n");
    System.out.print(
            resultProgression.stream().map(ComputationalResult::toString).collect(Collectors.joining("\n"))
    );

    System.out.println("\n\n\n\n\n");

  }
}
