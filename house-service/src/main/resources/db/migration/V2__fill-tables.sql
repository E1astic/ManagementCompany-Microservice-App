insert into streets(name) values('Средне-Московская');
insert into streets(name) values('Кольцовская');
insert into streets(name) values('Плехановская');

insert into houses(street_id, number) values(1, '69');
insert into houses(street_id, number) values(2, '45');
insert into houses(street_id, number) values(3, '29');

insert into apartments(house_id, entrance_num, floor_num, apartment_num, area)
    values(1, '5', 3, '135', 70.2);
insert into apartments(house_id, entrance_num, floor_num, apartment_num, area)
    values(2, '1', 10, '47', 59.8);
insert into apartments(house_id, entrance_num, floor_num, apartment_num, area)
    values(3, '2', 4, '72', 61.5);