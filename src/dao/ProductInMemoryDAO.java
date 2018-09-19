package dao;

import entity.Product;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductInMemoryDAO implements ProductDAO {
    @Override
    public List<Product> listAll() {
        String sql = "select * from Product";
        ResultSet rs = DBUtil.executeQuery(sql);
        List<Product> list = new ArrayList<Product>();
        Product model = null;
        try {
            while (rs.next()) {
                model = new Product(
                        rs.getInt("pId"),
                        rs.getString("pName"),
                        rs.getInt("tId"),
                        rs.getBigDecimal("pPrice"),
                        rs.getString("pPhoto"),
                        rs.getString("pRemark"),
                        rs.getInt("pState"));
                list.add(model);
            }
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getProductById(int tId) {
        String sql = "select * from Product where tId=?";
        Object[] in = {tId};
        Product model = null;
        ResultSet rs = DBUtil.executeQuery(sql,in);
        List<Product> list = new ArrayList<Product>();
        try {
            while (rs.next()) {
                model = new Product(
                        rs.getInt("pId"),
                        rs.getString("pName"),
                        rs.getInt("tId"),
                        rs.getBigDecimal("pPrice"),
                        rs.getString("pPhoto"),
                        rs.getString("pRemark"),
                        rs.getInt("pState"));
                list.add(model);
            }
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getProductByName(String pName) {
        String sql = "select * from Product where pName  like '%"+pName+"%'";
        Product model = null;
        ResultSet rs = DBUtil.executeQuery(sql);
        List<Product> list = new ArrayList<Product>();
        try {
            while (rs.next()) {
                model = new Product(
                        rs.getInt("pId"),
                        rs.getString("pName"),
                        rs.getInt("tId"),
                        rs.getBigDecimal("pPrice"),
                        rs.getString("pPhoto"),
                        rs.getString("pRemark"),
                        rs.getInt("pState"));
                list.add(model);
            }
            DBUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Product product) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int add(Product product) {
        return 0;
    }
}
