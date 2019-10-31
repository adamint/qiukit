package com.adamratzman.qiukit.samples;

import com.adamratzman.qiukit.Circuit;
import com.adamratzman.qiukit.ComputationalResult;
import com.adamratzman.qiukit.GateWithArgument;
import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.one.binary.Phase;

import java.util.List;
import java.util.stream.Collectors;

public class Circuits {
  public static void main(String[] args) {
    Circuit circuit = new Circuit(
            new GateWithArgument(new Phase(), Math.PI)
    );

    System.out.println(circuit);
    List<ComputationalResult> results = circuit.evaluate(Qubit.getQubit(Qubit.State.ZERO));
    System.out.println(results.stream().map(ComputationalResult::toString).collect(Collectors.joining("\n")));
    System.out.println("Result: " + results.get(results.size() - 1).getAfter());
  }
}
