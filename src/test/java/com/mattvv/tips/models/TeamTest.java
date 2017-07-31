package com.mattvv.tips.models;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class TeamTest {
  @Test
  public void shouldReturnTeams() {
    ImmutableList<ImmutableTeam> teams = Team.getTeams();
    assertThat(teams)
        .contains(ImmutableTeam.builder()
            .city("Essendon")
            .mascot("Bombers")
            .build());

    assertThat(teams.size()).isEqualTo(18);
  }

  @Test
  public void shouldPrintTeams() {
    ImmutableTeam team = ImmutableTeam.builder()
        .mascot("Bombers")
        .city("Essendon")
        .build();
    assertThat(team.toString()).isEqualTo("Team{city=Essendon, mascot=Bombers}");
  }

  @Test
  public void canUseWith() {
    ImmutableTeam team = ImmutableTeam.builder()
        .mascot("Bombers")
        .city("Essendon")
        .build();

    ImmutableTeam essendon = ImmutableTeam.builder()
        .city("Essendon")
        .mascot("Crows")
        .build();

    assertThat(essendon.withMascot("Bombers")).isEqualTo(team);
    assertThat(team.equals(essendon.withMascot("Bombers"))).isTrue();
    assertThat(team.hashCode()).isNotEqualTo(essendon.hashCode());
    assertThat(essendon.withCity("Adelaide").city()).isEqualTo("Adelaide");
    assertThat(essendon.mascot()).isEqualTo("Crows");
  }

  @Test(expected = IllegalStateException.class)
  public void shouldErrorIfNotAllParamsAreSet() {
    ImmutableTeam.builder()
        .mascot("Banana Man")
        .build();
  }

  @Test
  public void copyOfShouldWork() {
    ImmutableTeam team = ImmutableTeam.builder()
        .mascot("Cats")
        .city("Geelong")
        .build();

    assertThat(ImmutableTeam.copyOf(team)).isEqualTo(team);
    assertThat(ImmutableTeam.copyOf((Team) team)).isEqualTo(team);
    assertThat(ImmutableTeam.builder()
        .from(team)
        .build()).isEqualTo(team);
  }
}
