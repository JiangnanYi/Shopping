package dao;

import entity.Option;
import entity.POtion;

import java.util.List;

public interface OptionDAO {
    List<POtion> listAll();
    Option getOptionById(int id);
    List<Option> getOptionByName(String name);
    int update(Option option);
    boolean delete(int id);
    boolean add(Option option);
}
