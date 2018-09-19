package dao;

import entity.Logins;

import java.util.List;

public interface LoginsDAO {
    List<Logins> listAll();
    Logins getLoginsById(int id);
    List<Logins> getLoginsByName(String name);
    int update(Logins logins);
    int delete(int id);
    int add(Logins logins);
}
