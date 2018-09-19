package dao;

import entity.Ptype;

import java.util.List;

public interface PtypeDAO {
    List<Ptype> listAll();
    Ptype getPtypeById(int id);
    List<Ptype> getPtypeByName(String name);
    int update(Ptype ptype);
    int delete(int id);
    int add(Ptype ptype);
}
