CREATE TABLE IF NOT EXISTS product (id INT NOT NULL AUTO_INCREMENT, description VARCHAR(255), price NUMERIC(20, 2), PRIMARY KEY (id));

INSERT INTO product(description, price) VALUES ('Coca cola', 7.8);
INSERT INTO product(description, price) VALUES ('Pessi cola', 6.5);
INSERT INTO product(description, price) VALUES ('Adidas', 9.0);
INSERT INTO product(description, price) VALUES ('Nike', 11.1);
INSERT INTO product(description, price) VALUES ('Channel', 23.32);
