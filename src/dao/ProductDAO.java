package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> listAll();
    List<Product> getProductById(int id);
    List<Product> getProductByName(String name);
    int update(Product product);
    int delete(int id);
    int add(Product product);
}
