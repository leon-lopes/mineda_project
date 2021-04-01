INSERT INTO user (id, password, username, name, admin) VALUES (1, 'admin', 'admin', 'Admin', TRUE);
INSERT INTO user (id, password, username, name, admin) VALUES (2, 'user', 'user', 'User', FALSE);
INSERT INTO user (id, password, username, name, admin) VALUES (3, 'userb', 'userb', 'User B', FALSE);
INSERT INTO user (id, password, username, name, admin) VALUES (4, 'userc', 'userc', 'User C', FALSE);

INSERT INTO category (id, name) VALUES (1, 'cupidatat');
INSERT INTO category (id, name) VALUES (2, 'non');
INSERT INTO category (id, name) VALUES (3, 'Terror');

INSERT INTO book (id, owner_id, title, author) VALUES (1, 2, 'lorem ipsum dolor sit amet', 'author a');
INSERT INTO book (id, owner_id, title, author) VALUES (2, 3, 'consectetur adipiscing elit', 'author b');
INSERT INTO book (id, owner_id, title, author) VALUES (3, 3, 'sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 'author c');
INSERT INTO book (id, owner_id, title, author) VALUES (4, 4, 'ut enim ad minim veniam', 'author d');
INSERT INTO book (id, owner_id, title, author) VALUES (5, 4, 'quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat', 'author e');
INSERT INTO book (id, owner_id, title, author) VALUES (6, 4, 'duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur', 'author f');

INSERT INTO book_category (book_id, category_id) VALUES (1, 1);
INSERT INTO book_category (book_id, category_id) VALUES (1, 2);
INSERT INTO book_category (book_id, category_id) VALUES (2, 1);
INSERT INTO book_category (book_id, category_id) VALUES (2, 2);
INSERT INTO book_category (book_id, category_id) VALUES (3, 1);
INSERT INTO book_category (book_id, category_id) VALUES (3, 2);
INSERT INTO book_category (book_id, category_id) VALUES (4, 2);
INSERT INTO book_category (book_id, category_id) VALUES (5, 2);
INSERT INTO book_category (book_id, category_id) VALUES (6, 1);
