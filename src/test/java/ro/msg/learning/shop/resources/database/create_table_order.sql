CREATE TABLE IF NOT EXISTS `orders` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `shipped_from` int REFERENCES location(id),
    `customer` int REFERENCES customer(id),
    `created_at` timestamp,
    `country` varchar(100),
    `city` varchar(100),
    `county` varchar(100),
    `street_address` varchar(100)

);