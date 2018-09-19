package dao;

import entity.Logins;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginsInMemoryDAO implements LoginsDAO{
    @Override
    public List<Logins> listAll() {
        String sql="select * from Logins";
        ResultSet rs = DBUtil.executeQuery(sql);
        List<Logins> list=new ArrayList<Logins>();
        Logins model=null;
        try {
            while(rs.next()){
                model=new Logins(
                        rs.getInt("lId"),
                        rs.getString("lUser"),
                        rs.getString("lPwd"),
                        rs.getString("lPhone"),
                        rs.getString("lMailbox"));
                list.add(model);
            }
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Logins getLoginsById(int id) {
        return null;
    }

    @Override
    public List<Logins> getLoginsByName(String name) {
        return null;
    }

    @Override
    public int update(Logins logins) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int add(Logins logins) {
        String sql="insert into [Logins] values(?,?,?,?)";
        Object[] in={logins.getlUser(),logins.getlPwd(),logins.getlPhone(),logins.getlMailbox()};
        return DBUtil.executeUpdate(sql, in);
    }
    public int logins(String lUser,String lPwd){
        String sql = "select count(*)login from [Logins] where  lUser = ? and lPwd = ?";
        Object[] in = {lUser,lPwd};
        int count = 0;
        ResultSet rs = DBUtil.executeQuery(sql, in);
        try {
            while(rs.next()){
                count = rs.getInt("login");
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return count;
    }
    public int lid(String lUser,String lPwd){
        String sql = "select lId from [Logins] where  lUser = ? and lPwd = ?";
        Object[] in = {lUser,lPwd};
        int count = 0;
        ResultSet rs = DBUtil.executeQuery(sql, in);
        try {
            while(rs.next()){
                count = rs.getInt("lId");
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return count;
    }
}
