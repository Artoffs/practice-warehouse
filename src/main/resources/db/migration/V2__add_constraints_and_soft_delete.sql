ALTER TABLE shipment ALTER COLUMN status SET NOT NULL;
ALTER TABLE shipment ALTER COLUMN pickup_point_id SET NOT NULL;
ALTER TABLE shipment ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE product ALTER COLUMN supplier_id SET NOT NULL;
ALTER TABLE product ALTER COLUMN name SET NOT NULL;

ALTER TABLE stock ALTER COLUMN quantity SET NOT NULL;
ALTER TABLE stock ALTER COLUMN product_id SET NOT NULL;

ALTER TABLE customer_order ALTER COLUMN shipment_id SET NOT NULL;
ALTER TABLE customer_order ALTER COLUMN total_price SET NOT NULL;

ALTER TABLE order_items ALTER COLUMN order_id SET NOT NULL;
ALTER TABLE order_items ALTER COLUMN product_id SET NOT NULL;
ALTER TABLE order_items ALTER COLUMN quantity SET NOT NULL;
ALTER TABLE order_items ALTER COLUMN price_at_shipment SET NOT NULL;

ALTER TABLE pickup_point ALTER COLUMN address SET NOT NULL;


ALTER TABLE shipment DROP CONSTRAINT IF EXISTS shipment_pickup_point_id_fkey;
ALTER TABLE shipment
    ADD CONSTRAINT fk_shipment_pickup_point
        FOREIGN KEY (pickup_point_id)
            REFERENCES pickup_point(id)
            ON DELETE RESTRICT;


ALTER TABLE product DROP CONSTRAINT IF EXISTS product_supplier_id_fkey;
ALTER TABLE product
    ADD CONSTRAINT fk_product_supplier
        FOREIGN KEY (supplier_id)
            REFERENCES supplier(id)
            ON DELETE RESTRICT;


ALTER TABLE customer_order DROP CONSTRAINT IF EXISTS customer_order_shipment_id_fkey;
ALTER TABLE customer_order
    ADD CONSTRAINT fk_customer_order_shipment
        FOREIGN KEY (shipment_id)
            REFERENCES shipment(id)
            ON DELETE RESTRICT;



ALTER TABLE order_items DROP CONSTRAINT IF EXISTS order_items_product_id_fkey;
ALTER TABLE order_items
    ADD CONSTRAINT fk_order_items_product
        FOREIGN KEY (product_id)
            REFERENCES product(id)
            ON DELETE RESTRICT;


ALTER TABLE pickup_point ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE NOT NULL;