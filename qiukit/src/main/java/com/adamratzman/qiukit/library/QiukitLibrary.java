package com.adamratzman.qiukit.library;

import com.adamratzman.qiukit.Circuit;
import com.adamratzman.qiukit.Gate;
import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.operators.one.unary.Hadamard;
import com.adamratzman.qiukit.operators.one.unary.Read;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QiukitLibrary {
  public static Circuit hadRead() {
    return hadRead(new Random());
  }

  public static Circuit hadRead(Random random) {
    List<Circuit> gates = Arrays.asList(
            new Gate<Qubit, Qubit>(new Hadamard(random)),
            new Gate<Qubit, Qubit>(new Read(random))
    );
    return new Circuit(gates);
  }
}
