create database if not exists project_sprint_2_module_6_v1;
use project_sprint_2_module_6_v1;

create table if not exists promotion(
	id int primary key auto_increment,
	name varchar(250),
	is_delete bit default 0,
	image text,
	start_time varchar(50),
	end_time varchar(50),
	detail text,
	discount int
);

CREATE TABLE IF NOT EXISTS user (
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(200),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS user_role (
    username VARCHAR(50),
    role_id INT,
    is_delete BIT DEFAULT 0,
    FOREIGN KEY (username)
        REFERENCES user (username),
    FOREIGN KEY (role_id)
        REFERENCES role (id),
    PRIMARY KEY (username , role_id)
);
CREATE TABLE IF NOT EXISTS customer_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    is_delete BIT DEFAULT 0,
    day_of_birth VARCHAR(30),
    gender INT,
    id_card VARCHAR(12),
    email VARCHAR(100),
    address VARCHAR(200),
    phone_number VARCHAR(15),
    username VARCHAR(30) UNIQUE,
    customer_type_id INT,
    FOREIGN KEY (username)
        REFERENCES user (username),
    FOREIGN KEY (customer_type_id)
        REFERENCES customer_type (id)
);
CREATE TABLE IF NOT EXISTS beer_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    is_delete bit default 0
);
CREATE TABLE IF NOT EXISTS beer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150),
    image TEXT,
    trademark VARCHAR(50),
    made VARCHAR(50),
    alcohol DOUBLE,
    volume DOUBLE,
    amount INT,
    detail TEXT,
    price int,
    is_delete INT DEFAULT 0,
    beer_type_id INT,
    customer_id int,
    FOREIGN KEY (beer_type_id)
        REFERENCES beer_type (id),
        foreign key (customer_id)
        references customer(id)
)