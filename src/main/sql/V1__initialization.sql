
CREATE TABLE slick_example.game_types (
    id SERIAL PRIMARY KEY,
    type_name TEXT
);

CREATE TABLE slick_example.games (
    id SERIAL PRIMARY KEY,
    type_id INT REFERENCES slick_example.games (id) NOT NULL,
    name TEXT
);

INSERT INTO slick_example.game_types (type_name) VALUES
    ('RPG'), ('ACTION'), ('RTS');

INSERT INTO slick_example.games (type_id, name) values
    (2, 'Doom'), (1, 'Fallout'), (1, 'Tyranny');
