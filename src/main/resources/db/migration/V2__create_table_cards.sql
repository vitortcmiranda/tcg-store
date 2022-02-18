CREATE TABLE `cards` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `name` varchar(200) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` varchar(200) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `customer_id` int not null,
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);
