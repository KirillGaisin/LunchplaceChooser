package com.kgaisin.lunchchooser.service;

import com.kgaisin.lunchchooser.exceptions.DataNotPresentException;
import com.kgaisin.lunchchooser.exceptions.RestaurantNotFoundException;
import com.kgaisin.lunchchooser.exceptions.ValidationLimitException;
import com.kgaisin.lunchchooser.model.Choice;
import com.kgaisin.lunchchooser.model.Lunch;
import com.kgaisin.lunchchooser.model.Restaurant;
import com.kgaisin.lunchchooser.model.User;
import com.kgaisin.lunchchooser.repository.ChoiceRepository;
import com.kgaisin.lunchchooser.repository.LunchRepository;
import com.kgaisin.lunchchooser.utils.ChoiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Kirill Gaisin
 */

@Service
public class ChoiceService {

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalTime TIME_LIMIT = LocalTime.parse("11:00");
    private final Logger log = LoggerFactory.getLogger(ChoiceService.class);
    private final ChoiceRepository choiceRepository;
    private final LunchRepository lunchRepository;

    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository, LunchRepository lunchRepository) {
        this.choiceRepository = choiceRepository;
        this.lunchRepository = lunchRepository;
    }

    public Optional<Choice> getForUserAndDate(Long userId, LocalDate date) {
        log.debug("Authorized {}", userId);
        return choiceRepository.getForUserAndDate(userId, date);
    }

    public List<Lunch> getForRestaurantAndDate(Restaurant restaurant, LocalDate date) {
        return lunchRepository.findAllByRestaurantAndDate(restaurant, date);
    }

    @Transactional
    public ChoiceStatus save(User user, Restaurant restaurant) {
        ChoiceStatus choiceStatus = choiceRepository.getForUserAndDate(user.getId(), TODAY)
                .map(c -> {
                    c.setRestaurant(restaurant); //create
                    return new ChoiceStatus(c, false);
                })
                .orElseGet(() -> new ChoiceStatus(
                        new Choice(user, restaurant, TODAY), true)); //update

        choiceRepository.save(choiceStatus.getChoice());

        return choiceStatus;
    }

    @Transactional
    public ChoiceStatus saveAfterLimitTime(User user, Restaurant restaurant) {
        return choiceRepository.getForUserAndDate(user.getId(), TODAY)
                .map(c -> new ChoiceStatus(c, false))
                .orElseGet(() -> new ChoiceStatus(choiceRepository.save(new Choice(user, restaurant, TODAY)), true));
    }

    public Restaurant getCurrent(User user) throws RestaurantNotFoundException {
        return getForUserAndDate(user.getId(), TODAY).map(Choice::getRestaurant).orElseThrow(() -> new RestaurantNotFoundException("The restaurant was not chosen"));
    }

    public ChoiceStatus choiceStatus(User user, Restaurant restaurant) {

        if (restaurant == null) {
            log.info("Restaurant not fount");
            throw new ResourceNotFoundException("Restaurant not found");
        }

        if (getForRestaurantAndDate(restaurant, TODAY).isEmpty()) {
            log.info("The restaurant {} does not have lunch for choice", restaurant.getId());
            throw new DataNotPresentException("The restaurant does not have lunch for choice");
        }

        boolean limit = LocalTime.now().isAfter(TIME_LIMIT);
        ChoiceStatus choiceStatus = limit
                ? saveAfterLimitTime(user, restaurant)
                : save(user, restaurant);

        if (!choiceStatus.isCreated() && limit) {
            log.info("Choices time expired. Current time is {}", LocalTime.now().toString());
            throw new ValidationLimitException("Choices time expired");
        }

        return choiceStatus;
    }
}
