package com.kgaisin.lunchchooser.web;

import org.junit.jupiter.api.Test;

import static com.kgaisin.lunchchooser.testdata.LunchTestData.*;
import static com.kgaisin.lunchchooser.testdata.RestaurantTestData.RESTAURANT_0;
import static com.kgaisin.lunchchooser.testdata.RestaurantTestData.RESTAURANT_2;
import static com.kgaisin.lunchchooser.testdata.UserTestData.ADMIN;
import static com.kgaisin.lunchchooser.testdata.UserTestData.USER;

/**
 * @author Kirill Gaisin
 */

class LunchControllerTest extends AbstractControllerTest {

    @Override
    @Test
    void getAll() throws Exception {
        testGetAll(LUNCH_URL, USER);
    }

    @Override
    @Test
    void getById() throws Exception {
        testGetById(LUNCH_URL + LUNCH_0.getId(), USER);
    }

    @Override
    @Test
    void getIsNotFound() throws Exception {
        testGetIsNotFound(LUNCH_URL + 100, USER);
    }

    @Override
    @Test
    void create() throws Exception {
        testCreate(LUNCH_URL, ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapLunch(RESTAURANT_0, LOCAL_DATE, "New lunch", 200)));
    }

    @Override
    @Test
    void createIsConflict() throws Exception {
        testCreateIsConflict(LUNCH_URL, ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapLunch(RESTAURANT_0, LOCAL_DATE, "Kebab", 200)
                ));
    }

    @Override
    @Test
    void createIsForbidden() throws Exception {
        testCreateIsForbidden(LUNCH_URL, USER,
                objectMapper.writeValueAsString(
                        getStringObjectMapLunch(RESTAURANT_0, LOCAL_DATE, "New lunch", 200)));
    }

    @Override
    @Test
    void update() throws Exception {
        testUpdate(LUNCH_URL + LUNCH_0.getId(), ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapLunch(RESTAURANT_2, LOCAL_DATE, "Update lunch", 100)
                ));
    }

    @Override
    @Test
    void updatedIsConflict() throws Exception {
        testUpdateIsConflict(LUNCH_URL + LUNCH_0.getId(), ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapLunch(RESTAURANT_0, LOCAL_DATE, "Kebab", 100)
                ));
    }

    @Override
    @Test
    void updateIsForbidden() throws Exception {
        testUpdateIsForbidden(LUNCH_URL + LUNCH_0.getId(), USER,
                objectMapper.writeValueAsString(
                        getStringObjectMapLunch(RESTAURANT_2, LOCAL_DATE, "Kebab", 100)
                ));
    }

    @Override
    @Test
    void deleted() throws Exception {
        testDelete(LUNCH_URL + LUNCH_0.getId(), ADMIN);
    }

    @Override
    @Test
    void deletedIsForbidden() throws Exception {
        testDeleteIsForbidden(LUNCH_URL + LUNCH_0.getId(), USER);
    }
}
