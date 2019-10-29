package com.adamratzman.qiukit;

public class ComputationalResult <A, B> {
  private A before;
  private B after;

  public ComputationalResult(A before, B after) {
    this.before = before;
    this.after = after;
  }

  public A getBefore() {
    return before;
  }

  public B getAfter() {
    return after;
  }

  @Override
  public String toString() {
    return "Computation(before=" + before + ", after=" + after + ")";
  }
}
