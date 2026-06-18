-- Очистка таблиц (если нужно перезаполнить)
TRUNCATE TABLE order_items CASCADE;
TRUNCATE TABLE customer_order CASCADE;
TRUNCATE TABLE shipment CASCADE;
TRUNCATE TABLE stock CASCADE;
TRUNCATE TABLE product CASCADE;
TRUNCATE TABLE supplier CASCADE;
TRUNCATE TABLE pickup_point CASCADE;

-- 1. Пункты выдачи
INSERT INTO pickup_point (address) VALUES
                                       ('г. Москва, ул. Тверская, д. 15'),
                                       ('г. Санкт-Петербург, Невский пр-т, д. 28'),
                                       ('г. Новосибирск, ул. Ленина, д. 10'),
                                       ('г. Екатеринбург, ул. Малышева, д. 5'),
                                       ('г. Казань, ул. Баумана, д. 20');

-- 2. Поставщики
INSERT INTO supplier (name, email, phone) VALUES
                                              ('ООО "Электроника Плюс"', 'info@eplus.ru', '+7(495)111-22-33'),
                                              ('ЗАО "Компьютерный Мир"', 'sales@compworld.ru', '+7(812)444-55-66'),
                                              ('ИП "Смарт Технолоджис"', 'order@smarttech.ru', '+7(383)777-88-99'),
                                              ('ООО "Домашняя Техника"', 'info@hometech.ru', '+7(343)222-33-44'),
                                              ('АО "Цифровые Решения"', 'support@digisolve.ru', '+7(843)555-66-77');

-- 3. Товары
INSERT INTO product (supplier_id, name, description, price) VALUES
                                                                (1, 'Смартфон Galaxy S23', '8/256 ГБ, AMOLED, 6.1"', 549.99),
                                                                (1, 'Наушники Buds Pro', 'Беспроводные, шумоподавление', 89.99),
                                                                (2, 'Ноутбук XPS 15', 'i7-12700H, 16GB, RTX 3050', 1299.99),
                                                                (2, 'Монитор 27" 4K', 'IPS, 144Hz, HDR600', 499.99),
                                                                (3, 'Умная колонка Echo', 'Голосовой помощник, Bluetooth', 49.99),
                                                                (3, 'Фитнес-браслет Band 7', 'Пульсометр, шагомер, GPS', 34.99),
                                                                (4, 'Пылесос Robot X10', 'Автономный, лазерная навигация', 299.99),
                                                                (4, 'Кофемашина Barista Pro', 'Капсульная, давление 15 бар', 179.99),
                                                                (5, 'Планшет Tab S9', '11", 128GB, S Pen в комплекте', 399.99),
                                                                (5, 'SSD 1TB M.2', 'NVMe, чтение 7000 МБ/с', 119.99);

-- 4. Остатки на складе
INSERT INTO stock (product_id, quantity) VALUES
                                             (1, 15),
                                             (2, 30),
                                             (3, 8),
                                             (4, 12),
                                             (5, 25),
                                             (6, 40),
                                             (7, 6),
                                             (8, 18),
                                             (9, 10),
                                             (10, 22);

-- 5. Отгрузки (shipments)
INSERT INTO shipment (pickup_point_id, status, created_at) VALUES
                                                               (1, 'Доставлено', '2026-01-10'),
                                                               (2, 'В пути', '2026-01-15'),
                                                               (3, 'Ожидание', '2026-01-20'),
                                                               (4, 'Доставлено', '2026-01-25'),
                                                               (5, 'Отменен', '2026-02-01'),
                                                               (1, 'Доставлено', '2026-02-05'),
                                                               (2, 'В пути', '2026-02-10'),
                                                               (3, 'Ожидание', '2026-02-15');

-- 6. Заказы клиентов
INSERT INTO customer_order (shipment_id, total_price) VALUES
                                                          (1, 789.98),
                                                          (2, 1349.98),
                                                          (3, 84.98),
                                                          (4, 499.99),
                                                          (5, 399.99),
                                                          (6, 1199.98),
                                                          (7, 179.99),
                                                          (8, 549.99);

-- 7. Товары в заказах
INSERT INTO order_items (order_id, product_id, quantity, price_at_shipment) VALUES
-- Заказ 1 (shipment 1)
(1, 1, 1, 549.99),
(1, 2, 2, 89.99),
-- Заказ 2 (shipment 2)
(2, 3, 1, 1299.99),
(2, 4, 1, 49.99), -- Умная колонка по скидке 49.99
-- Заказ 3 (shipment 3)
(3, 6, 2, 34.99),
(3, 5, 1, 14.99), -- Колонка по акции
-- Заказ 4 (shipment 4)
(4, 4, 1, 499.99),
-- Заказ 5 (shipment 5)
(5, 9, 1, 399.99),
-- Заказ 6 (shipment 6)
(6, 3, 1, 1199.98),
(6, 8, 1, 0.00), -- Подарок
-- Заказ 7 (shipment 7)
(7, 8, 1, 179.99),
-- Заказ 8 (shipment 8)
(8, 1, 1, 549.99);