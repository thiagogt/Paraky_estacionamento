CREATE TABLE version_bd ( id INTEGER IDENTITY, version VARCHAR(256));

CREATE TABLE client ( 
id_client INTEGER IDENTITY, 
name VARCHAR(256) not null,
last_name VARCHAR(256), 
cpf VARCHAR(256) UNIQUE not null, 
birthday_date date,
tel_1 VARCHAR(256),
tel_2 VARCHAR(256),
email VARCHAR(256),
id_owner_parking_space INTEGER);

CREATE TABLE car( 
id_car INTEGER IDENTITY,
id_client_car INTEGER,
year_manufacture INTEGER,
color VARCHAR(256) not null,
model VARCHAR(256), 
car_plate VARCHAR(256) UNIQUE not null, 
car_brand VARCHAR(256));

CREATE TABLE daily_payment( 
id_daily_payment INTEGER IDENTITY,
id_car_charged INTEGER NOT NULL,
cost double,
checkin TIMESTAMP,
checkout TIMESTAMP);

CREATE TABLE parking_space( 
id_parking_space INTEGER IDENTITY,
id_client_owner INTEGER NOT NULL,
pay_day integer,
type_parking_space VARCHAR(256),
contract_date DATE,
parking_space_cost double);

CREATE TABLE monthly_payment( 
id_monthly_payment INTEGER IDENTITY,
id_parking_space INTEGER NOT NULL,
payment_status boolean,
payment_date DATE);

CREATE TABLE adress( 
id_adress INTEGER IDENTITY,
id_client_adress INTEGER NOT NULL,
street VARCHAR(256),
number VARCHAR(256),
complement VARCHAR(256),
neighborhood VARCHAR(256),
city VARCHAR(256),
country VARCHAR(256));

CREATE SEQUENCE CAR_SEQUENCE START WITH 1 INCREMENT BY 1


