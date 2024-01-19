DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'phone_item') THEN
        CREATE TABLE phone_item (
                                    id SERIAL PRIMARY KEY,
                                    manufacturer VARCHAR(255),
                                    name VARCHAR(255),
                                    description VARCHAR(255),
                                    storage_space_gb BIGINT,
                                    quantity INTEGER,
                                    price INTEGER
        );

        INSERT INTO phone_item (manufacturer, name, description, storage_space_gb, quantity, price)
        SELECT 'Samsung', 'Galaxy S21', 'Flagship smartphone', 128, 10, 899
        WHERE EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'phone_item');

        INSERT INTO phone_item (manufacturer, name, description, storage_space_gb, quantity, price)
        SELECT 'Apple', 'iPhone 15', 'Latest iPhone model', 256, 15, 1099
        WHERE EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'phone_item');

        INSERT INTO phone_item (manufacturer, name, description, storage_space_gb, quantity, price)
        SELECT 'Google', 'Pixel 6', 'High-end Android phone', 128, 8, 799
        WHERE EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'phone_item');
    END IF;
END $$;