package com.wugk.me;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

/**
 * Created by 10041713 on 2018/7/13.
 */
public class JdbiMain {
/*    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:sqlserver://127.0.0.1;databaseName=xxx", "xxx", "xxx");
        jdbi.installPlugin(new SqlObjectPlugin());
        List<ME> mes = jdbi.withHandle(handle->{
            return handle.createQuery("select * from me").map(new MeMapper()).list();
        });
        for (ME me : mes) {
            System.out.println(me.getName());
        }
    }*/

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:sqlserver://127.0.0.1;databaseName=xxx", "xxx", "xxxx");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            MEDao meDao = handle.attach(MEDao.class);
            List<ME> mes = meDao.getAll();
            for (ME me : mes) {
                System.out.println(me.getName());
            }
        }
    }
}
