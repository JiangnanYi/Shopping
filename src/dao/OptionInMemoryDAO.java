package dao;

import entity.Option;
import entity.POtion;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OptionInMemoryDAO implements OptionDAO {

    public List<POtion> listAll() {
        String sql = "select o.oId,p.pPhoto,p.pName,p.pPrice,o.buy_num,o.oCount from [Option] o inner join Product p on o.pId=p.pId where o.oState=0";
        ResultSet rs = DBUtil.executeQuery(sql);
        List<POtion> list = new ArrayList<POtion>();
        POtion model = null;
        try {
            while (rs.next()) {
                model = new POtion(
                        rs.getInt("oId"),
                        rs.getString("pPhoto"),
                        rs.getString("pName"),
                        rs.getBigDecimal("pPrice"),
                        rs.getInt("buy_num"),
                        rs.getBigDecimal("oCount"));
                list.add(model);
            }
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Option getOptionById(int id) {
        return null;
    }

    @Override
    public List<Option> getOptionByName(String name) {
        return null;
    }

    @Override
    public int update(Option option) {
        return 0;
    }
    public boolean update(int oId, int buy_num) {
        String sql = "update [Option] set buy_num = ? where oId = ?";
        Object[] in = new Object[]{buy_num, oId};
        if (DBUtil.executeUpdate(sql, in) > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean delete(int id) {
        String sql="delete from [Option] where oId=?";
        Object[] in =  new Object[]{id};
        if(DBUtil.executeUpdate(sql,in)>0){
        return true;
        }
        return false;
    }

    @Override
    public boolean add(Option option) {
        return false;
    }

    public boolean addoption(POtion potion) {
        //先查询数据库中有没有这一条数据
        String sqlIs = "select * from [Option] o inner join Product p on o.pId= p.pId   where o.pId =? and o.lId=? and o.oState=0";
        ResultSet rs = DBUtil.executeQuery(sqlIs,new Object[]{potion.getpId(),potion.getlId()});
        int count = -1;
        try {
            if(rs.next()){  //存在该商品记录，获得数量、单价
                count = rs.getInt("buy_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(count==-1){
            //如果没有则添加
            String sql = "insert into [Option](pId,lId,buy_num) values(?,?,?)";
            Object[] in = {potion.getpId(), potion.getlId(),1};
            if(DBUtil.executeUpdate(sql, in)>0) {
                return true;
            }
            else{
                return false;
            }
        }
        //否则就修改它的数量
        else{
            String sql = "update [Option] set buy_num= ? where lId = ?  and  pId = ? ";
            count = potion.getBuy_num()+count;
            Object[] in = new Object[]{count,potion.getlId(),potion.getpId()};
            if(DBUtil.executeUpdate(sql,in)>0){
                return true;
            }else{
                return false;
            }
        }

    }

    //价格查询方法
    public List<POtion> num_price(String str, int lId) {
        String sql = "select * from Product p inner join [Option] o on p.pId = o.pId where oId in (" + str + ") and lId=? ";
        List<POtion> list = new ArrayList<POtion>();
        POtion pro = null;
        ResultSet rs = DBUtil.executeQuery(sql, new Object[]{lId});
        //int oId, String pPhoto, String pName, BigDecimal pPrice, int buy_num
        try {
            while (rs.next()) {
                pro = new POtion(
                        rs.getInt("oId"),
                        rs.getString("pPhoto"),
                        rs.getString("pName"),
                        rs.getBigDecimal("pPrice"),
                        rs.getInt("buy_num")
                );
                list.add(pro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getCartCount(int lId){
        String sql = "select sum(buy_num) from [Option] where lId = ?  and  oState= 0;";
        ResultSet rs = DBUtil.executeQuery(sql,new Object[]{lId});
        int count = 0;
        try {
            if(rs.next()){  //获得数据
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
