package com.adamratzman.qiukit.library;

import com.adamratzman.qiukit.Circuit;
import com.adamratzman.qiukit.Gate;
import com.adamratzman.qiukit.operators.unary.Hadamard;
import com.adamratzman.qiukit.operators.unary.Read;
import com.adamratzman.qiukit.qubit.Qubit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QiukitLibrary {
  public static Circuit generateRandomQubit(Random random) {
    Qubit qubit = Qubit.getQubit(Qubit.State.ZERO, random);
    List<Gate> gates = Arrays.asList(
            new Gate<Qubit, Qubit>(Collections.singletonList(qubit), new Hadamard(random)),
            new Gate<Qubit, Qubit>(Collections.singletonList(qubit), new Read(random))
    );
    return new Circuit(gates);
  }
}
