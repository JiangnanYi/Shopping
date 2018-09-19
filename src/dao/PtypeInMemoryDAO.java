package dao;

import entity.Ptype;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PtypeInMemoryDAO implements PtypeDAO {
    @Override
    public List<Ptype> listAll() {
        String sql = "select * from Ptype";
        ResultSet rs = DBUtil.executeQuery(sql);
        List<Ptype> list = new ArrayList<Ptype>();
        Ptype model = null;
        try {
            while (rs.next()) {
                model = new Ptype(
                        rs.getInt("tId"),
                        rs.getString("tName"));
                list.add(model);
            }
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Ptype getPtypeById(int id) {
        return null;
    }

    @Override
    public List<Ptype> getPtypeByName(String name) {
        return null;
    }

    @Override
    public int update(Ptype ptype) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int add(Ptype ptype) {
        return 0;
    }
}
