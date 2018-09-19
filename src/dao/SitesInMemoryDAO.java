package dao;

import entity.Sites;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SitesInMemoryDAO implements SitesDAO {
    @Override
    public List<Sites> listAll() {
        return null;
    }

    public List<Sites> listAlls(int lId) {
        String sql = "select * from Sites where lId=?";
        Sites sites = null;
        List<Sites> list = new ArrayList<Sites>();
        ResultSet rs = DBUtil.executeQuery(sql,new Object[]{lId});
        //   int sId, int lId, String sName, String sPhone, String sLocatio
        try {
            while (rs.next()){
                sites = new Sites(
                        rs.getInt("sId"),
                        rs.getInt("lId"),
                        rs.getString("sName"),
                        rs.getString("sPhone"),
                        rs.getString("sLocation")
                );
                list.add(sites);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public Sites getSitesById(int id) {
        return null;
    }

    @Override
    public List<Sites> getSitesByName(String name) {
        return null;
    }

    @Override
    public int update(Sites sites) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int add(Sites sites) {
        String sql = "insert into Sites(lId,sName,sPhone,sLocation) values(?,?,?,?)";
        Object[] in = new Object[]{sites.getlId(),sites.getsName(),sites.getsPhone(),sites.getsLocation()};
        DBUtil.executeUpdate(sql,in);
            return 0;
    }

}
