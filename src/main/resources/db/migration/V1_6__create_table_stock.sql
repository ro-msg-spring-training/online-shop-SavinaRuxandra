CREATE TABLE IF NOT EXISTS `stock` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product` int REFERENCES product(id),
    `location` int REFERENCES location(id),
    `quantity` int

);