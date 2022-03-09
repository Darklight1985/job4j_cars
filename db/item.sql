CREATE TABLE body_type (
                           id SERIAL PRIMARY KEY,
                           name TEXT
);

CREATE TABLE mark_avto (
                           id SERIAL PRIMARY KEY,
                           name TEXT
);

CREATE TABLE cars (
                      id SERIAL PRIMARY KEY,
                      bodytype_id int references body_type(id),
                      markavto_id int references mark_avto(id)
);

CREATE TABLE item (
                           id SERIAL PRIMARY KEY,
                           description TEXT,
                           photo TEXT,
                           car_id int references cars(id)
);