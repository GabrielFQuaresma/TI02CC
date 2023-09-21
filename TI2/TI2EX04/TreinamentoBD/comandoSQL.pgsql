CREATE TABLE pokemon (
    codpokedex serial PRIMARY KEY,
    nome VARCHAR(50),
    tipo VARCHAR(50),
    hp INTEGER,
    attack INTEGER,
    speed INTEGER
);

INSERT INTO pokemon (codpokedex, nome, tipo, hp, attack, speed) VALUES (1, 'Charmander', 'Fogo', 10, 20, 30);
INSERT INTO pokemon (codpokedex, nome, tipo, hp, attack, speed) VALUES (2, 'Squirtle', 'Agua', 10, 20, 30);
INSERT INTO pokemon (codpokedex, nome, tipo, hp, attack, speed) VALUES (3, 'Bulbasaur', 'Planta', 30, 20, 30);
SELECT * from pokemon;
SELECT * from pokemon WHERE nome = 'Bulbasaur'