package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.QubitOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gate<E, T> extends Circuit {
  List<E> qubits;
  private QubitOperator<E, T> qubitOperator;

  public Gate(QubitOperator<E, T> qubitOperator) {
    this(Collections.emptyList(), qubitOperator);
  }

  public Gate(List<E> qubits, QubitOperator<E, T> qubitOperator) {
    super(new ArrayList<>());
    this.qubits = qubits;
    this.qubitOperator = qubitOperator;
    addCircuit(this);
  }

  private ComputationalResult<E, T> invoke(E before) {
    T after = qubitOperator.apply(qubits);

    return new ComputationalResult<>(before, after, qubitOperator.getName());
  }

  @Override
  public List<ComputationalResult> evaluate(Object initial) {
    return Collections.singletonList(invoke((E) initial));
  }

  @Override
  public String toString() {
    return "Gate(" + qubits.toString() + ", " + qubitOperator.toString() + ")";
  }
}
