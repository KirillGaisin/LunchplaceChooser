DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;
DELETE FROM lunch;
DELETE FROM choice_restaurant;

-- user@yandex.ru : user
-- admin@gmail.com: admin

INSERT INTO users (id, email, name, password) VALUES (0, 'test_user@testmail.com', 'User', 'testuserpass'), (1, 'test_admin@testmail.com', 'Admin', 'testadminpass');
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 0), ('ROLE_ADMIN', 1);
INSERT INTO restaurant (id, name) VALUES (0,'McDonalds'), (1,'KFC'), (2,'Papa Johns');
INSERT INTO lunch (ID, LUNCH_DATE, RESTAURANT_ID, NAME, PRICE) VALUES (0, today(), 0, 'Big Mac', 100), (1, '2021-01-11', 0, 'Cheeseburger', 75), (2, today(), 1, 'Chicken Wings', 80), (3, '2021-01-11', 2, 'Margherita', 100), (4, today(), 2, 'Super Papa', 150), (5, '2018-05-23', 1, 'Box Master', 120);
INSERT INTO CHOICE_RESTAURANT (ID, USER_ID, CHOICE_DATE, RESTAURANT_ID) VALUES (0, 0, '2021-01-11', 0);