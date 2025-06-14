insert into roles(name) values ('ROLE_USER');
insert into roles(name) values ('ROLE_ADMIN');

insert into users(surname, name, patronymic, birth_date, document_num, email, phone, password, apartment_id, role_id)
values ('admin','admin','admin', '10-10-1980','234519864622','admin@mail.ru','+7777777777', '$2a$10$SexTb3WX5/FJDbCPyLolQeG3HUrojcWwejLNbVVYuWaJ71RgwaPCe', 3, 2);
insert into users(surname, name, patronymic, birth_date, document_num, email, phone, password, apartment_id, role_id)
values ('user','user','user', '5-5-1995','5331246209','user@mail.ru','+78888888888', '$2a$10$S8u4Tre0ESYf7imYimaXCupCybpCUGb9s0M1zt/JckCRXsMEFcJw6', 1, 1);