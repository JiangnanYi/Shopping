package dao;

import entity.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> listAll();
    Order getOrderById(int id);
    List<Order> getOrderByName(String name);
    int update(Order order);
    int delete(int id);
    int add(Order order);
}
