package com.adamratzman.qiukit.samples;

import com.adamratzman.qiukit.Qubit;
import com.adamratzman.qiukit.library.QiukitLibrary;

public class SimpleQKDSample {
  public static void main(String[] args) {
    System.out.println(tryToCatchSpy(true, true));
  }

  public static boolean tryToCatchSpy(boolean isSpyActive, boolean spyHad) {
    Qubit alice = Qubit.getQubit(Qubit.State.ZERO);
    Qubit fiber;

    // Generate two random bits
    Qubit sendHad = (Qubit) QiukitLibrary.hadRead().evaluateDiscardingSteps(Qubit.getQubit(Qubit.State.ZERO));
    Qubit randomBitBob = (Qubit) QiukitLibrary.hadRead().evaluateDiscardingSteps(Qubit.getQubit(Qubit.State.ZERO));

    Qubit sendVal = (Qubit) QiukitLibrary.hadRead().evaluateDiscardingSteps(Qubit.getQubit(Qubit.State.ZERO));
    if (sendVal.read().getState() == Qubit.State.ONE) alice = alice.not();
    if (sendHad.read().getState() == Qubit.State.ONE) alice = alice.hadamard();
    fiber = alice;

    if (isSpyActive) {
      if (spyHad) fiber = fiber.hadamard();
      Qubit readQubit = fiber.read();
      fiber.write(readQubit.getState());
      if (spyHad) fiber = fiber.hadamard();
    }

    if (randomBitBob.getState() == Qubit.State.ONE) fiber = fiber.hadamard();

    Qubit readBit = fiber.read();

    if (sendHad.equals(randomBitBob)) {
        return !sendVal.equals(readBit);
    }

    return false;
  }
}
