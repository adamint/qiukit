package com.adamratzman.qiukit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Circuit {
  private List<Circuit> circuits;

  public Circuit(Circuit... circuits) {
    this.circuits = new ArrayList<>();
    this.circuits.addAll(Arrays.asList(circuits));
  }

  public Circuit(List<Circuit> circuits) {
    this.circuits = circuits;
  }

  public Circuit addCircuit(Gate gate) {
    circuits.add(gate);
    return this;
  }

  public List<Circuit> getCircuits() {
    return circuits;
  }

  public void setCircuits(List<Circuit> circuits) {
    this.circuits = circuits;
  }

  public List<ComputationalResult> evaluate(Object initial) {
    if (circuits.isEmpty()) return null;
    List<ComputationalResult> results = new ArrayList<>();
    Object supply = initial;
    for (Circuit circuit : circuits) {
      if (circuit instanceof Gate && supply instanceof Qubit) {
        Gate gate = (Gate) circuit;
        ArrayList<Qubit> qubits = new ArrayList<Qubit>(gate.qubits);
        qubits.add((Qubit) supply);
        gate.qubits = qubits;
      }
      List<ComputationalResult> circuitResults = circuit.evaluate(supply);
      results.addAll(circuitResults);
      supply = circuitResults.get(circuitResults.size() - 1).getAfter();
    }

    return results;
  }

  @Override
  public String toString() {
    return circuits.toString();
  }
}
