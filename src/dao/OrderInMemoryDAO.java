package dao;

import entity.Order;
import entity.POtion;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderInMemoryDAO implements OrderDAO {
    @Override
    public List<Order> listAll() {

        return null;
    }

    public List<POtion> listAll(String str) {
        String sql = "select o.oId,p.pPhoto,p.pName,p.pPrice,o.buy_num,o.oCount from [Option] o inner join Product p on o.pId=p.pId where oId in (" + str + ") and o.oState=0";
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
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> getOrderByName(String name) {
        return null;
    }

    @Override
    public int update(Order order) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int add(Order order) {
        return 0;
    }

    /**
     * 订单编号  地址 *修改购物车中购物项的状态（c_state 1） 购物车订单号的关联
     */
    public boolean addOrderUpdCart(int sId, Object oId) {
        String[] oIds = oId.toString().split(",");

        //  String UUID.randomUUID(),int a_id,String c_id
        //修改状态  update `ShoppingCart` set `c_state` = 1 where `c_id` in (1,2,3);
        // 1、生成订单 一个商品一个订单
        boolean bool = false;


        for (int i = 0; i < oIds.length; i++) {
            int o_Id = Integer.parseInt(oIds[i]);  //当前商品的c_id
            String orId = UUID.randomUUID().toString(); //生成一个订单号
            System.out.println(orId+","+o_Id+","+sId);
            String sqlOrder = "insert into [Order](orId,oId,[sId]) values(?,?,?)";
            Object[] inOrder = new Object[]{orId, o_Id, sId};
            if (DBUtil.executeUpdate(sqlOrder, inOrder) > 0) {  //添加订单成功
                bool = true;
            } else {  //添加订单失败，直接返回 false 不需要再继续执行以下操作
                return false;
            }
            //2、修改购物车状态
            String sqlUpdState = "update [Option] set oState = ? where oId=?";
            Object[] inUpdState = new Object[]{1, o_Id};
            if (DBUtil.executeUpdate(sqlUpdState, inUpdState) > 0) {   //修改状态成功
                bool = true;
            } else {
                return false;
            }
            //3、修改订单状
            String sqlUpdState2 = "update [Order] set oState = ? where oId=? ";
            Object[] inUpdState2 = new Object[]{1, o_Id};
            if (DBUtil.executeUpdate(sqlUpdState2, inUpdState2) > 0) {   //修改状态成功
                bool = true;
            } else {
                return false;
            }
        }
        return bool;
    }
}
