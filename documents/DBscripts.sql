SHOW DATABASES;
CREATE DATABASE AtilaInventoryDB;
USE AtilaInventoryDB;

DROP TABLE raw_materials;

CREATE TABLE raw_materials (
    material_id INT NOT NULL AUTO_INCREMENT,
    category INT,
    group_name INT CHECK (group_name IN (1, 2)),
    catalog_num VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(50) NOT NULL,
    concentration DOUBLE,
    receive_date DATE,
    location VARCHAR(50),
    owner VARCHAR(50),
    website VARCHAR(255),
    threshold INT NOT NULL,
    amount_in_stock INT NOT NULL,
    PRIMARY KEY (material_id)
);

CREATE TABLE requests (
    request_id INT NOT NULL AUTO_INCREMENT,
    item_description VARCHAR(255),
    request_category INT,
	project VARCHAR(255),
    project_description VARCHAR(255),
    purpose INT,
    request_by VARCHAR(50),
    done_by VARCHAR(50),
    request_amount DOUBLE,
    fulfilled_amount DOUBLE,
    price_per_unit DOUBLE,
    request_date DATE,
    fulfilled_date DATE,
    status INT CHECK (status IN (1, 2)),
    material_id INT,
    PRIMARY KEY (request_id),
    FOREIGN KEY (material_id) REFERENCES raw_materials (material_id)
);

CREATE TABLE RD_materials (
    RD_material_id INT NOT NULL AUTO_INCREMENT,
    p_material_id INT,
    category INT,
    group_name INT CHECK (group_name IN (1, 2)),
    catalog_num VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(50) NOT NULL,
    concentration DOUBLE,
    receive_date DATE,
    location VARCHAR(50),
    owner VARCHAR(50),
    website VARCHAR(255),
    threshold INT NOT NULL,
    amount_in_stock INT NOT NULL,
    PRIMARY KEY (RD_material_id),
    FOREIGN KEY (p_material_id) REFERENCES raw_materials (material_id)
);

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('mary','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('wayne','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('wayne','ROLE_EMPLOYEE'),
('wayne','ROLE_MANAGER'),
('wayne','ROLE_ADMIN');

DROP TABLE Products;
DROP TABLE components;
DROP TABLE intermediate_components;

CREATE TABLE products (
	product_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100),
    version_description VARCHAR(255),
    PRIMARY KEY (product_id)
);

CREATE TABLE product_records (
	product_record_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100),
    product_id INT,
    lot_number VARCHAR(50),
    manufacture_date DATE,
    amount_in_stock INT,
    PRIMARY KEY (product_record_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE components (
    component_id INT NOT NULL AUTO_INCREMENT,
    component_name VARCHAR(50) NOT NULL,
	version_description VARCHAR(255),
    PRIMARY KEY (component_id)
);

CREATE TABLE component_records (
	component_record_id INT NOT NULL AUTO_INCREMENT,
    component_name VARCHAR(100),
    component_id INT,
    lot_number VARCHAR(50),
    manufacture_date DATE,
    amount_in_stock INT,
    PRIMARY KEY (component_record_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id)
);

CREATE TABLE prerequisite (
	prerequisite_id INT NOT NULL AUTO_INCREMENT,
    component_id INT,
    intermediate_component_id INT,
    amount_per_rxn DOUBLE,
    PRIMARY KEY (prerequisite_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id),
    FOREIGN KEY (intermediate_component_id) REFERENCES components (component_id)
);

CREATE TABLE assembly_by (
    assembly_id INT NOT NULL AUTO_INCREMENT,
    product_id INT,
    component_id INT,
    amount_per_assay INT,
    PRIMARY KEY (assembly_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE recipe_items (
    recipe_item_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    amount_per_rxn DOUBLE,
    material_id INT DEFAULT NULL,
	component_id INT DEFAULT NULL,
    PRIMARY KEY (recipe_item_id),
    FOREIGN KEY (material_id) REFERENCES raw_materials (material_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id)
);

CREATE TABLE manufacture_records (
	manufacture_record_id INT NOT NULL AUTO_INCREMENT,
    component_name VARCHAR(50) NOT NULL,
    manufacture_date DATE,
    manufacture_scale INT,
    owner VARCHAR(50),
    status INT CHECK (status IN (1, 2, 3)),
    component_record_id INT,
    product_record_id INT,
    PRIMARY KEY (manufacture_record_id),
    FOREIGN KEY (component_record_id) REFERENCES component_records (component_record_id),
    FOREIGN KEY (product_record_id) REFERENCES product_records (product_record_id)
);

CREATE TABLE manufacture_record_details (
	record_detail_id INT NOT NULL AUTO_INCREMENT,
    item_name VARCHAR(100) NOT NULL,
    amount_per_rxn DOUBLE,
    total_vol DOUBLE,
    manufacture_record_id INT,
    component_record_id INT DEFAULT NULL,
    raw_material_id INT DEFAULT NULL,
    PRIMARY KEY (record_detail_id),
    FOREIGN KEY (raw_material_id) REFERENCES raw_materials (material_id),
    FOREIGN KEY (component_record_id) REFERENCES component_records (component_record_id),
    FOREIGN KEY (manufacture_record_id) REFERENCES manufacture_records (manufacture_record_id)
);

SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;



