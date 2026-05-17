CREATE TABLE fighters (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    height NUMERIC(10, 2),
    birth_date DATE NOT NULL,
    weight NUMERIC(10, 2),
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE fighter_competitions (
    fighter_id UUID NOT NULL,
    competition VARCHAR(255) NOT NULL,
    FOREIGN KEY (fighter_id) REFERENCES fighters(id)
);

CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE fighter_category (
    fighter_id UUID NOT NULL,
    category_id UUID NOT NULL,
    PRIMARY KEY (fighter_id, category_id),
    FOREIGN KEY (fighter_id) REFERENCES fighters(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);