package com.adamratzman.qiukit;

public class ComputationalResult <A, B> {
  private A before;
  private B after;
  private String gate;

  public ComputationalResult(A before, B after, String gate) {
    this.before = before;
    this.after = after;
    this.gate = gate;
  }

  public A getBefore() {
    return before;
  }

  public B getAfter() {
    return after;
  }

  public String getGate() {
    return gate;
  }

  @Override
  public String toString() {
    return "Computation(before=" + before + ", after=" + after + ")";
  }
}
