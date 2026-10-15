package com.company;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Map;

public interface RestaurantService {
    public void add(Restaurant restaurant) throws Exception;
    public void update(Restaurant restaurant) throws Exception;
    public void delete(String Id) throws Exception;
    public Entity findById(String restaurantId) throws Exception;
    public Collection<Restaurant> findByName(String name) throws Exception;
    public Collection<Restaurant> findVyCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
