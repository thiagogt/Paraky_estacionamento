ALTER TABLE client ADD CONSTRAINT fk_relative_client FOREIGN KEY (id_owner_parking_space) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE car ADD CONSTRAINT fk_car_client FOREIGN KEY (id_client_car) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE daily_payment ADD CONSTRAINT fk_car_charged FOREIGN KEY (id_car_charged) REFERENCES car (id_car) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE parking_space ADD CONSTRAINT fk_client_owner_space FOREIGN KEY (id_client_owner) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE monthly_payment ADD CONSTRAINT fk_payment_space FOREIGN KEY (id_parking_space) REFERENCES parking_space (id_parking_space) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE adress ADD CONSTRAINT fk_adress_client FOREIGN KEY (id_client_adress) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE;
