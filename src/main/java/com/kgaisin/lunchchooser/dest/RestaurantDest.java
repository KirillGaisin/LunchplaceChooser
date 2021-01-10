package com.kgaisin.lunchchooser.dest;

import com.kgaisin.lunchchooser.model.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Kirill Gaisin
 */

@NoArgsConstructor
@Data
public class RestaurantDest {
    private List<LunchDest> lunches;
    private String name;

    public RestaurantDest(Restaurant restaurant, List<LunchDest> lunches) {
        this.name = restaurant.getName();
        this.lunches = lunches;
    }
}
