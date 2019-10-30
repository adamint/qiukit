package com.adamratzman.qiukit.samples;

import com.adamratzman.qiukit.Circuit;
import com.adamratzman.qiukit.ComputationalResult;
import com.adamratzman.qiukit.Gate;
import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.one.unary.Hadamard;
import com.adamratzman.qiukit.operators.one.unary.Read;
import com.adamratzman.qiukit.operators.one.unary.RootOfNot;
import com.adamratzman.qiukit.operators.one.unary.TPhase;

import java.util.List;
import java.util.stream.Collectors;

public class Circuits {
  public static void main(String[] args) {
    Circuit circuit = new Circuit(
            new Gate<Qubit, Qubit>(new Hadamard()),
            new Gate<Qubit, Qubit>(new TPhase()),
            new Gate<Qubit, Qubit>(new RootOfNot()),
            new Gate<Qubit, Qubit>(new Hadamard()),
            new Gate<Qubit, Qubit>(new Read())
    );

    System.out.println(circuit);
    List<ComputationalResult> results = circuit.evaluate(Qubit.getQubit(Qubit.State.ZERO));
    System.out.println(results.stream().map(ComputationalResult::toString).collect(Collectors.joining("\n")));
    System.out.println("Result: " + results.get(results.size() - 1).getAfter());
  }
}
