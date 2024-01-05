CREATE TABLE IF NOT EXISTS product (id INT NOT NULL AUTO_INCREMENT, description VARCHAR(255), price NUMERIC(20, 2), restock DATETIME, PRIMARY KEY (id));

INSERT INTO product(restock, description, price) VALUES ('2024-01-01 12:00:00', 'Coca cola', 7.8);
INSERT INTO product(restock, description, price) VALUES ('2024-01-15 12:00:00', 'Pessi cola', 6.5);
INSERT INTO product(restock, description, price) VALUES ('2024-01-15 12:00:00', 'Adidas', 9.0);
INSERT INTO product(restock, description, price) VALUES ('2024-01-15 12:00:00', 'Nike', 11.1);
INSERT INTO product(restock, description, price) VALUES ('2024-01-30 12:00:00', 'Channel', 23.32);

INSERT INTO product(restock, description, price) VALUES ('2024-02-01 12:00:00', 'Coca cola 1', 7.8);
INSERT INTO product(restock, description, price) VALUES ('2024-02-15 12:00:00', 'Pessi cola 1', 6.5);
INSERT INTO product(restock, description, price) VALUES ('2024-02-15 12:00:00', 'Adidas 1', 9.0);
INSERT INTO product(restock, description, price) VALUES ('2024-02-15 12:00:00', 'Nike 1', 11.1);
INSERT INTO product(restock, description, price) VALUES ('2024-02-25 12:00:00', 'Channel 1', 23.32);
