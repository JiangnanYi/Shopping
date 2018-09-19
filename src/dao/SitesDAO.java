package dao;

import entity.Sites;

import java.util.List;

public interface SitesDAO {
    List<Sites> listAll();
    Sites getSitesById(int id);
    List<Sites> getSitesByName(String name);
    int update(Sites sites);
    int delete(int id);
    int add(Sites sites);
}
