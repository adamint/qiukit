package com.adamratzman.qiukit;

import com.adamratzman.qiukit.operators.QubitBinaryOperator;
import com.adamratzman.qiukit.operators.QubitOperator;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GateWithArgument<E, S, T> extends Gate<Pair<E, S>, T> {
  private S object;
  private QubitBinaryOperator<E, S, T> qubitOperator;

  public GateWithArgument(QubitOperator<Pair<E, S>, T> qubitOperator, S argument) {
    super(qubitOperator);
    this.object = argument;

    this.qubitOperator = (QubitBinaryOperator) qubitOperator;
    addCircuit(this);
  }

  private ComputationalResult<E, T> invoke(E before) {
    T after = qubitOperator.apply(Collections.singletonList(new Pair<>(before, object)));

    return new ComputationalResult<>(before, after, qubitOperator.getName() + "(" + object + ")");
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
