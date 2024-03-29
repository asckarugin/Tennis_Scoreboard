CREATE TABLE players(
                        id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                        name varchar(128)
);

CREATE TABLE matches(
                        id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                        first_player int NOT NULL,
                        second_player int NOT NULL,
                        winner_player int NOT NULL,
                        CONSTRAINT fk_first_player FOREIGN KEY (first_player) REFERENCES players(id),
                        CONSTRAINT fk_second_player FOREIGN KEY (second_player) REFERENCES players(id),
                        CONSTRAINT fk_winner_player FOREIGN KEY (winner_player) REFERENCES players(id)
);