CREATE TABLE IF NOT EXISTS areas
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS favorites_employers
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR,
    date_create DATE NOT NULL,
    description TEXT,
    area_id     INTEGER references areas (id),
    comment     TEXT,
    views_count INTEGER
);

CREATE TABLE IF NOT EXISTS vacancies_employers
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS favorites_vacancies
(
    id              INTEGER PRIMARY KEY,
    name            VARCHAR,
    date_create     DATE NOT NULL,
    area_id         INTEGER references areas (id),
    salary_to       INTEGER,
    salary_from     INTEGER,
    salary_currency VARCHAR,
    salary_gross    BOOLEAN,
    created_at      VARCHAR,
    employer_id     INTEGER references vacancies_employers (id),
    views_count     INTEGER,
    comment         TEXT
);
