create table aircrafts (
    aircraft_code VARCHAR(3) not null,
    model         JSON       not null,
    range         INTEGER    not null
) as select * from csvread('src/main/resources/database/airtrans/aircrafts_data.csv');

create table airports (
    airport_code VARCHAR(3)      not null,
    airport_name JSON            not null,
    city         JSON            not null,
    coordinates  VARCHAR(128)    not null,
    timezone     VARCHAR(1024)   not null
) as select * from csvread('src/main/resources/database/airtrans/airports_data.csv');

create table boarding_passes (
    ticket_no   VARCHAR(13) not null,
    flight_id   INTEGER     not null,
    boarding_no INTEGER     not null,
    seat_no     VARCHAR(4)  not null
) as select * from csvread('src/main/resources/database/airtrans/boarding_passes.csv');

create table bookings (
    book_ref     VARCHAR(6)               not null,
    book_date    TIMESTAMP WITH TIME ZONE not null,
    total_amount NUMERIC(10,2)            not null
) as select * from csvread('src/main/resources/database/airtrans/bookings.csv');

create table flights (
    flight_id           INTEGER                  primary key,
    flight_no           VARCHAR(6)               not null,
    scheduled_departure TIMESTAMP WITH TIME ZONE not null,
    scheduled_arrival   TIMESTAMP WITH TIME ZONE not null,
    departure_airport   VARCHAR(3)               not null,
    arrival_airport     VARCHAR(3)               not null,
    status              VARCHAR(20)              not null,
    aircraft_code       VARCHAR(3)               not null,
    actual_departure    TIMESTAMP WITH TIME ZONE null,
    actual_arrival      TIMESTAMP WITH TIME ZONE null
) as select * from csvread('src/main/resources/database/airtrans/flights.csv', 'X1,X2,X3,X4,X5,X6,X7,X8,X9,X10', 'charset=UTF-8 fieldSeparator=,');

create table seats (
    aircraft_code   VARCHAR(3)  not null,
    seat_no         VARCHAR(4)  not null,
    fare_conditions VARCHAR(10) not null
) as select * from csvread('src/main/resources/database/airtrans/seats.csv');

create table ticket_flights (
    ticket_no       VARCHAR(13)   not null,
    flight_id       INTEGER       not null,
    fare_conditions varchar(10)   not null,
    amount          NUMERIC(10,2) not null
) as select * from csvread('src/main/resources/database/airtrans/ticket_flights.csv');

create table tickets (
    ticket_no      VARCHAR(13)    not null,
    book_ref       VARCHAR(6)     not null,
    passenger_id   VARCHAR(20)    not null,
    passenger_name VARCHAR(1024)  not null,
    contact_data   JSON           null
) as select * from csvread('src/main/resources/database/airtrans/tickets.csv');

--insert into flights(flight_id, flight_no, scheduled_departure, scheduled_arrival, departure_airport, arrival_airport, status, aircraft_code, actual_departure, actual_arrival) select * from csvread('src/main/resources/database/airtrans/flights.csv');
--select * from csvread('src/main/resources/database/airtrans/flights.csv');