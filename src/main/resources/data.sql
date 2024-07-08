INSERT INTO halls (name, address, phone)
VALUES ('ЛЕНДОК большой зал', 'наб. Крюкова канала 12', '6557357'),
       ('Цифербург', 'наб. реки Фонтанки 20', '4546857');

INSERT INTO actors (firstname, lastname, phone)
VALUES ('Иван', 'Сорокин', '+79345434552'),
       ('Дарья', 'Левицкая', '+79324747556'),
       ('Семен', 'Дробитько', '+79985464733'),
       ('Вячеслав', 'Пискунов', '+79244658575'),
       ('Николай', 'Терехов', '+74358575394'),
       ('Мария', 'Ольшанская', '+76564939375');

INSERT INTO performances (name, description, hall_id)
VALUES ('Камера Обскура', 'Двухчасовой спектакль по произведению Набокова', 1),
       ('Евгений Онегин', 'Музыкальный спекталь', 2),
       ('Вишневый сад', 'Новая интерпретация классической пьесы Чехова', 1);

INSERT INTO actors_performances
VALUES (1, 1),
       (2, 1),
       (6, 1),
       (4, 1),
       (2, 2),
       (3, 2),
       (5, 2),
       (6, 2),
       (1, 3),
       (6, 3),
       (3, 3);