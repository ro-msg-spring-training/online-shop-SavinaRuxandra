INSERT INTO `supplier` (name) VALUES
  ('ElectricShop'),
  ('PullAndBear'),
  ('Sephora');

INSERT INTO `product_category` (name, description) VALUES
  ('electronics', 'cheap'),
  ('clothes', 'cute'),
  ('Makeup', 'very nice');

INSERT INTO `product` (name, description, price, weight, category, supplier, image_Url) VALUES
  ('laptop DELL', 'great', 100, 2, 1, 1, 'laptopUrl'),
  ('shirt', 'cute', 20, 0.5, 2, 2, 'shirtUrl'),
  ('lipstick', 'red', 25, 0.1, 3, 3, 'lipstickUrl');