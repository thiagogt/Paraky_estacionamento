
INSERT INTO version_bd ( id , version) values(1,'1.0');

INSERT INTO client (id_client,name,last_name,cpf,birthday_date,tel_1 ,tel_2 ,email,id_owner_parking_space ) values (1,'usuarioTeste','primeiro','123456789','2008-08-22 00:00:00.000000000','12345-555','1234-555','teste@mail.com',null);
INSERT INTO client (id_client,name,last_name,cpf,birthday_date,tel_1 ,tel_2 ,email,id_owner_parking_space ) values (2,'usuarioTeste','segundo','123456780','2008-08-22 00:00:00.000000000','12345-555','1234-555','teste@mail.com',1);

INSERT INTO car (id_car ,id_client_car,year_manufacture ,color,model,car_plate,car_brand) values (1,1,2000,'green','pegoult 1.8','LCY1884','Pegoult');

INSERT INTO daily_payment(id_daily_payment,id_car_charged,cost,checkin,checkout) values (1,1,4.50,'2008-08-22 03:00:00.000000000','2008-08-22 05:30:20.000000000');

INSERT INTO parking_space(id_parking_space ,id_client_owner,pay_day,type_parking_space,contract_date,parking_space_cost) values (1,1,13,'carro','2008-08-22 05:30:20.000000000',200.00);

INSERT INTO monthly_payment(id_monthly_payment,id_parking_space, payment_status,payment_date ) values(1,1,'pago','2008-09-13 12:00:00.000000000');
INSERT INTO monthly_payment(id_monthly_payment,id_parking_space, payment_status,payment_date ) values(2,1,'devendo','2008-10-13 12:00:00.000000000');

INSERT INTO adress(id_adress,id_client_adress,street,number,complement,neighborhood,city,country) values(1,1,'Rua teste','001A','apto23','Limoeiro','São Paulo','Brasil');