package com.kgaisin.lunchchooser.service;

import com.kgaisin.lunchchooser.model.Restaurant;
import com.kgaisin.lunchchooser.repository.LunchRepository;
import com.kgaisin.lunchchooser.dest.LunchDest;
import com.kgaisin.lunchchooser.dest.RestaurantDest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author Kirill Gaisin
 */

@Service
public class RestaurantService {
    private static final LocalDate LOCAL_CURRENT_DATE = LocalDate.now();
    private final Logger log = LoggerFactory.getLogger(ChoiceService.class);
    private final LunchRepository lunchRepository;

    @Autowired
    public RestaurantService(LunchRepository lunchRepository) {
        this.lunchRepository = lunchRepository;
    }

    public RestaurantDest getWithLunchToday(Restaurant restaurant) {
        log.debug("Restaurant {} with lunch at {}", restaurant.getId(), LOCAL_CURRENT_DATE.toString());
        return new RestaurantDest(restaurant,
                lunchRepository.findAllByRestaurantAndDate(restaurant, LOCAL_CURRENT_DATE)
                        .stream().map(LunchDest::new).collect(Collectors.toList()));
    }
}
