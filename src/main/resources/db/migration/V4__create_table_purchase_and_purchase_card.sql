CREATE TABLE `purchase` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `customer_id` int NOT NULL,
  `nfe` varchar(255),
  `price` decimal(10,2) NOT NULL,
  `created_at` DATETIME NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);


CREATE TABLE `purchase_card` (
  `purchase_id` int NOT NULL,
  `card_id` int NOT NULL,
  FOREIGN KEY (purchase_id) REFERENCES purchase(id),
  PRIMARY KEY (purchase_id, card_id)
);