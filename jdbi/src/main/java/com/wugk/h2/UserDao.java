package com.wugk.h2;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


public interface UserDao {

    @SqlUpdate("CREATE TABLE user (id INTEGER PRIMARY KEY, name VARCHAR)")
    void createTable();

    @SqlUpdate("INSERT INTO user(id, name) VALUES(:id, :name)")
    void update(@Bind("id") Integer id, @Bind("name") String name);

    @SqlQuery("select * from user")
    @RegisterRowMapper(UserMapper.class)
    List<User> getAll();
}
