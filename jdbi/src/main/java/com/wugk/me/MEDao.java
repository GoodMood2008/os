package com.wugk.me;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;


public interface MEDao {
    @SqlQuery("select * from me")
    @RegisterRowMapper(MeMapper.class)
    List<ME> getAll();
}
