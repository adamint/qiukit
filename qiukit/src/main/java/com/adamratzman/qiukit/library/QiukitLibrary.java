package com.adamratzman.qiukit.library;

import com.adamratzman.qiukit.Circuit;
import com.adamratzman.qiukit.Gate;
import com.adamratzman.qiukit.operators.one.unary.Hadamard;
import com.adamratzman.qiukit.operators.one.unary.Read;
import com.adamratzman.qiukit.Qubit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QiukitLibrary {
  public static Circuit generateRandomQubit() {
    return generateRandomQubit(new Random());
  }

  public static Circuit generateRandomQubit(Random random) {
    Qubit qubit = Qubit.getQubit(Qubit.State.ZERO, random);
    List<Circuit> gates = Arrays.asList(
            new Gate<Qubit, Qubit>(Collections.singletonList(qubit), new Hadamard(random)),
            new Gate<Qubit, Qubit>(Collections.singletonList(qubit), new Read(random))
    );
    return new Circuit(gates);
  }
}
