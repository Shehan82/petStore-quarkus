CREATE DATABASE IF NOT EXISTS petstore;
USE petstore;
CREATE TABLE pet
(
    pet_id int NOT NULL AUTO_INCREMENT,
    pet_age int,
    pet_name varchar(255),
    pet_type varchar(255),
    PRIMARY KEY (id)
);

INSERT INTO pet VALUES
(1,4,"Sisi","Cat"),
(2,6,"Boola","Dog"),
(3,1,"Peththappu","Rabbit");