CREATE TABLE IF NOT EXISTS link_information (

    id VARCHAR(40) PRIMARY KEY,
    short_code VARCHAR(20) UNIQUE,
    original_url TEXT NOT NULL,
    clicks INTEGER
);