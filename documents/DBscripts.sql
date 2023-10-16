SHOW DATABASES;
USE AtilaInventoryDB;

DROP TABLE raw_materials;

CREATE TABLE raw_materials (
    material_id INT NOT NULL AUTO_INCREMENT,
    group_name INT CHECK (group_name IN (1, 2)),
    catalog_num VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(50) NOT NULL,
    concentration DOUBLE,
    receive_date DATE,
    threshold INT NOT NULL,
    amount_in_stock INT NOT NULL,
    PRIMARY KEY (material_id)
);


TRUNCATE TABLE raw_materials;

INSERT INTO raw_materials VALUES 
	(1,1,"B0538B","10x Mg free isothermal buffer","NEB",10.0,'2023-10-04',1000,100000),
    (2,1,"T8787-250ML","Triton™ X-100(T8787-250ML)","Sigma",1,'2022-10-04',900,9000),
    (3,1,"15568025","Invitrogen™ UltraPure™ 1M Tris-HCI, pH 8.0","FisherSci",1,'2022-12-19',10000,1000000),
	(4,1,"c4706-50g","TCEP","Sigma",1,'2023-06-15',100,300);

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
	component_id INT,
    prerequisite_id INT,
    PRIMARY KEY (component_id, prerequisite_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id),
    FOREIGN KEY (prerequisite_id) REFERENCES components (component_id)
);

CREATE TABLE assembly_by (
    product_id INT,
    component_id INT,
    PRIMARY KEY (product_id, component_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE recipe_items (
    recipe_item_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    amount_per_rxn DOUBLE,
    material_id INT DEFAULT NULL,
	component_id INT DEFAULT NULL,
    i_component_id INT DEFAULT NULL,
    PRIMARY KEY (recipe_item_id),
    FOREIGN KEY (material_id) REFERENCES raw_materials (material_id),
    FOREIGN KEY (component_id) REFERENCES components (component_id)
);

