package com.mattvv.tips.daos;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.common.collect.ImmutableList;
import com.mattvv.tips.models.ImmutableTeam;

public class DatastoreTeamsDao implements TeamsDao {
  private Datastore datastore;
  private KeyFactory keyFactory;

  public DatastoreTeamsDao() {
    datastore = DatastoreOptions.getDefaultInstance().getService();
    keyFactory = datastore.newKeyFactory().setKind("Team");
  }

  @Override
  public Long createTeam(ImmutableTeam team) {
    IncompleteKey key = keyFactory.newKey();
    FullEntity<IncompleteKey> incBookEntity = Entity.newBuilder(key)
        .set("city", team.city())
        .set("mascot", team.mascot())
        .build();
    Entity bookEntity = datastore.add(incBookEntity);
    return bookEntity.getKey().getId();
  }

  @Override
  public void updateTeam(ImmutableTeam team) {
    Key key = keyFactory.newKey(team.id());
    Entity entity = Entity.newBuilder(key)
        .set("city", team.id())
        .set("mascot", team.mascot())
        .build();
    datastore.update(entity);
  }

  @Override
  public ImmutableTeam getTeam(Long bookId) {
    Entity bookEntity = datastore.get(keyFactory.newKey(bookId));
    return entityToTeam(bookEntity);
  }

  @Override
  public void deleteTeam(Long teamId) {
    Key key = keyFactory.newKey(teamId);
    datastore.delete(key);
  }

  @Override
  public ImmutableList<ImmutableTeam> listTeams() {
    Query<Entity> query = Query.newEntityQueryBuilder()
        .setKind("Team")
        .setLimit(18)
        .setOrderBy(OrderBy.asc("city"))
        .build();
    QueryResults<Entity> resultList = datastore.run(query);
    return entitiesToTeams(resultList);
  }

  ImmutableTeam entityToTeam(Entity entity) {
    return ImmutableTeam.builder()
        .city(entity.getString("city"))
        .mascot(entity.getString("mascot"))
        .id(entity.getKey().getId())
        .build();
  }

  ImmutableList<ImmutableTeam> entitiesToTeams(QueryResults<Entity> resultList) {
    ImmutableList.Builder<ImmutableTeam> teamsBuilder = new ImmutableList.Builder<>();
    while (resultList.hasNext()) {
      teamsBuilder.add(entityToTeam(resultList.next()));
    }
    return teamsBuilder.build();
  }
}
