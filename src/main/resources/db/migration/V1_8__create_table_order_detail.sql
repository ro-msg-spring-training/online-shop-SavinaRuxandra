CREATE TABLE IF NOT EXISTS `order_detail` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `orders` int REFERENCES `orders`(id),
    `product` int REFERENCES product(id),
    `quantity` int

);

ALTER TABLE `order_detail` ADD CONSTRAINT UQ_order_product UNIQUE (`orders`, product)