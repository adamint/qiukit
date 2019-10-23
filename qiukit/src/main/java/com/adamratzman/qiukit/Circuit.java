package com.adamratzman.qiukit;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
  private List<Gate> gates;

  public Circuit(List<Gate> gates) {
    this.gates = gates;
  }

  public Circuit addGate(Gate gate) {
    gates.add(gate);
    return this;
  }

  public List<Gate> getGates() {
    return gates;
  }

  public void setGates(List<Gate> gates) {
    this.gates = gates;
  }

  public List<ComputationalResult> invoke(Object obj) {
    if (gates.isEmpty()) return null;
    List<ComputationalResult> results = new ArrayList<>();
    Object supply = obj;
    for (Gate gate : gates) {
      ComputationalResult result = gate.invoke(supply);
      results.add(result);
      supply = result.getAfter();
    }

    return results;
  }

  @Override
  public String toString() {
    return gates.toString();
  }
}
