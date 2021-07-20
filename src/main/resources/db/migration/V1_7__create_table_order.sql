CREATE TABLE IF NOT EXISTS `order` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `shipped_from` int REFERENCES location(id),
    `customer` int REFERENCES customer(id),
    `created_at` timestamp,
    `address_country` varchar(100),
    `address_city` varchar(100),
    `address_county` varchar(100),
    `address_street_address` varchar(100)

);