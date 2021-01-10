package com.kgaisin.lunchchooser.testdata;

import com.kgaisin.lunchchooser.model.Restaurant;

import java.util.HashMap;
import java.util.Map;

import static com.kgaisin.lunchchooser.web.AbstractControllerTest.REST_URL;

/**
 * @author Kirill Gaisin
 */


public class RestaurantTestData {
    public static final String RESTAURANT_URL = REST_URL + "/restaurants/";

    public static final Restaurant RESTAURANT_0 = new Restaurant(0L, "McDonalds");
    public static final Restaurant RESTAURANT_1 = new Restaurant(1L, "Burger King");
    public static final Restaurant RESTAURANT_2 = new Restaurant(2L, "KFC");

    public static Map<String, Object> getStringObjectMapRestaurant(String name) {
        return new HashMap<>() {{
            put("name", name);
        }};
    }
}
