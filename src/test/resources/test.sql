CREATE TABLE IF NOT EXISTS actors (
                        id SERIAL PRIMARY KEY,
                        firstname VARCHAR(25) NOT NULL,
                        lastname VARCHAR(25) NOT NULL,
                        phone VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS halls (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(30) NOT NULL,
                       address VARCHAR(80) NOT NULL,
                       phone VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS performances (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(30) NOT NULL,
                              description VARCHAR(80) NOT NULL,
                              hall_id INT,
                              FOREIGN KEY (hall_id) REFERENCES halls (id)
                                  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS actors_performances (
                                     actor_id INT NOT NULL,
                                     performance_id INT NOT NULL,
                                     PRIMARY KEY (actor_id, performance_id),
                                     FOREIGN KEY (actor_id) REFERENCES actors (id)
                                         ON DELETE CASCADE ON UPDATE CASCADE,
                                     FOREIGN KEY (performance_id) REFERENCES performances (id)
                                         ON DELETE CASCADE ON UPDATE CASCADE
);