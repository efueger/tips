package com.mattvv.tips.daos;

import com.google.common.collect.ImmutableList;
import com.mattvv.tips.models.ImmutableTeam;

public interface TeamsDao {
  Long createTeam(ImmutableTeam team);
  ImmutableTeam getTeam(Long teamId);
  void updateTeam(ImmutableTeam team);
  void deleteTeam(Long teamId);
  ImmutableList<ImmutableTeam> listTeams();
}
