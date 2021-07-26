CREATE TABLE IF NOT EXISTS `product` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100),
    `description` varchar(100),
    `price` decimal(5,2),
    `weight` double,
    `category` int REFERENCES product_category(id),
    `supplier` int REFERENCES supplier(id),
    `image_url` varchar(100)

);