package com.mattvv.tips.models;

import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
public abstract class Team {
  public abstract String city();
  public abstract String mascot();

  @Nullable
  public abstract Long id();

  public static ImmutableList<ImmutableTeam> getTeams() {
    ImmutableList.Builder<ImmutableTeam> teamBuilder = new ImmutableList.Builder<>();
    teamBuilder.add(ImmutableTeam.builder().city("Adelaide").mascot("Crows").build());
    teamBuilder.add(ImmutableTeam.builder().city("Brisbane").mascot("Lions").build());
    teamBuilder.add(ImmutableTeam.builder().city("Carlton").mascot("Blues").build());
    teamBuilder.add(ImmutableTeam.builder().city("Collingwood").mascot("Magpies").build());
    teamBuilder.add(ImmutableTeam.builder().city("Essendon").mascot("Bombers").build());
    teamBuilder.add(ImmutableTeam.builder().city("Fremantle").mascot("Dockers").build());
    teamBuilder.add(ImmutableTeam.builder().city("Geelong").mascot("Cats").build());
    teamBuilder.add(ImmutableTeam.builder().city("Gold Coast").mascot("Suns").build());
    teamBuilder
        .add(ImmutableTeam.builder().city("Greater Western Sydney").mascot("Giants").build());
    teamBuilder.add(ImmutableTeam.builder().city("Hawthorn").mascot("Hawks").build());
    teamBuilder.add(ImmutableTeam.builder().city("Melbourne").mascot("Demons").build());
    teamBuilder.add(ImmutableTeam.builder().city("North Melbourne").mascot("Kangaroos").build());
    teamBuilder.add(ImmutableTeam.builder().city("Port Adelaide").mascot("Power").build());
    teamBuilder.add(ImmutableTeam.builder().city("Richmond").mascot("Tigers").build());
    teamBuilder.add(ImmutableTeam.builder().city("St Kilda").mascot("Saints").build());
    teamBuilder.add(ImmutableTeam.builder().city("Sydney").mascot("Swans").build());
    teamBuilder.add(ImmutableTeam.builder().city("West Coast").mascot("Eagles").build());
    teamBuilder.add(ImmutableTeam.builder().city("Western").mascot("Bulldogs").build());
    return teamBuilder.build();
  }
}
