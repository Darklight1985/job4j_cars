CREATE TABLE body_type (
                           id SERIAL PRIMARY KEY,
                           name TEXT
);

CREATE TABLE engines (
                           id SERIAL PRIMARY KEY,
                           name TEXT
);

CREATE TABLE driver_type (
                           id SERIAL PRIMARY KEY,
                           name TEXT
);

CREATE TABLE models (
                           id SERIAL PRIMARY KEY,
                           name TEXT
);

CREATE TABLE cars (
                      id SERIAL PRIMARY KEY,
                      power text,
                      bodytype_id int references body_type(id),
                      drivertype_id int references driver_type(id),
                      engine_id int references engines(id),
                      model_id int references models(id)
);

CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        name TEXT,
                        password TEXT
);

CREATE TABLE ads (
                           id SERIAL PRIMARY KEY,
                           created timestamp,
                           description TEXT,
                           done boolean,
                           photo TEXT,
                           car_id int references cars(id),
                           user_id int references users(id)
);