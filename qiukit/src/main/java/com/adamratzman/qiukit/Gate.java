package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.QubitOperator;
import com.adamratzman.qiukit.qubit.Qubit;

import java.util.List;

public class Gate<E, T> {
  private List<Qubit> qubits;
  private QubitOperator<Qubit, T> qubitOperator;

  public Gate(List<Qubit> qubits, QubitOperator<Qubit, T> qubitOperator) {
    this.qubits = qubits;
    this.qubitOperator = qubitOperator;
  }

  ComputationalResult<E, T> invoke(E before) {
    T after = qubitOperator.apply(qubits);

    return new ComputationalResult<>(before, after);
  }

  @Override
  public String toString() {
    return "Gate(" + qubits.toString() + ", " + qubitOperator.toString() + ")";
  }
}
