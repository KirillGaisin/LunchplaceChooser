package com.kgaisin.lunchchooser.dest;

import com.kgaisin.lunchchooser.model.Lunch;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kirill Gaisin
 */

@Data
@NoArgsConstructor
public class LunchDest {
    private String name;
    private Integer price;

    public LunchDest(Lunch lunch) {
        this.name = lunch.getName();
        this.price = lunch.getPrice();
    }
}
