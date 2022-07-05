-- Create marketplace keyspace
CREATE KEYSPACE IF NOT EXISTS marketplace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

-- Create wish tables
CREATE TABLE IF NOT EXISTS marketplace.wish_by_customer (
    customer_id uuid,
    product_id uuid,
    name text,
    description text,
    amount int,
    PRIMARY KEY ((customer_id), product_id)
);
