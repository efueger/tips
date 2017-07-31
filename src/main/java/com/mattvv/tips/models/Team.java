package com.mattvv.tips.models;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Team {
  public abstract String city();
  public abstract String mascot();
}
