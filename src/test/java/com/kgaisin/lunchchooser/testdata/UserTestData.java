package com.kgaisin.lunchchooser.testdata;

import com.kgaisin.lunchchooser.model.Role;
import com.kgaisin.lunchchooser.model.User;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import static com.kgaisin.lunchchooser.web.AbstractControllerTest.REST_URL;

/**
 * @author Kirill Gaisin
 */

public class UserTestData {
    public static final String USERS_URL = REST_URL + "/users/";

    public static final Long START_SEQ = 0L;
    public static final Long USER_ID = START_SEQ;
    public static final Long ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "test_user@testmail.com", "testuserpass", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "test_admin@testmail.com", "testadminpass", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static Map<String, Object> getCreatedUser() {
        return getStringObjectMapUser("New user", "user@local.loc", "password", "ROLE_USER", "ROLE_ADMIN");
    }

    public static Map<String, Object> getUpdateUser() {
        return getStringObjectMapUser("Update user", "test_user@testmail.com", "testuserpass", "ROLE_USER");
    }

    private static Map<String, Object> getStringObjectMapUser(String name, String email, String password, @NotNull String... roles) {
        return new HashMap<>() {{
            put("name", name);
            put("email", email);
            put("password", password);
            put("roles", roles);
        }};
    }
}
