package com.wugk.h2;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JdbiH2Main {


    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test", "root", "");
        jdbi.installPlugin(new H2DatabasePlugin());
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao userDao = handle.attach(UserDao.class);
            userDao.createTable();
            userDao.update(1, "xiaoming");
            userDao.update(2, "xiaohong");
            userDao.getAll().forEach(user -> System.out.println(user.toString()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
