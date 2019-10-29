package com.adamratzman.qiukit.samples;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.library.QiukitLibrary;

public class SimpleQKDSample {
  public static void main(String[] args) {
    Qubit alice = Qubit.getQubit(Qubit.State.ZERO);

    // Generate two random bits
    Qubit sendHad = (Qubit) QiukitLibrary.generateRandomQubit().evaluate();
    Qubit sendVal = (Qubit) QiukitLibrary.generateRandomQubit().evaluate();

    if (sendVal.getState() == Qubit.State.ONE) alice = alice.not();
    if (sendHad.getState() == Qubit.State.ONE) alice = alice.hadamard();

  }
}
