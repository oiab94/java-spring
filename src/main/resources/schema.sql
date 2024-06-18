DROP TABLE IF EXISTS "widgets";

CREATE TABLE "widgets" (
    "id"            SERIAL PRIMARY KEY,
    "name"          TEXT NOT NULL,
    "description"   TEXT
);