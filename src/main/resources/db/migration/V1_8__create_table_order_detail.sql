CREATE TABLE IF NOT EXISTS `order_detail` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `order` int REFERENCES order(id),
    `product` int REFERENCES product(id),
    `quantity` int

);