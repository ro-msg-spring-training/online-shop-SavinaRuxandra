CREATE TABLE IF NOT EXISTS `revenue` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `location` int REFERENCES location(id),
    `date` date,
    `sum` decimal

);