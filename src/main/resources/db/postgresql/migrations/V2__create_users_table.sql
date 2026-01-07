BEGIN;
-- contents of V1__init_schema.sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(355),
    email VARCHAR(255) UNIQUE,
    username VARCHAR(255) UNIQUE,
    phone VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    is_active BOOLEAN

--    created_at TIMESTAMP NOT NULL DEFAULT now()
);
COMMIT;