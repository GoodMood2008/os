package com.wugk.me;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MeMapper implements RowMapper<ME> {
    @Override
    public ME map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        ME me = new ME();
        me.setName(resultSet.getString("NAME"));
        return me;
    }
}
