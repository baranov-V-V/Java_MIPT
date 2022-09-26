create table aircrafts (
    aircraft_code VARCHAR(3) not null,
    model         JSON       not null,
    range         INTEGER    not null
);

create table airports (
    airport_code VARCHAR(3)      not null,
    airport_name JSON            not null,
    city         JSON            not null,
    coordinates  GEOMETRY(POINT) not null,
    timezone     VARCHAR(1024)   not null
);

create table boarding_passes (
    ticket_no   VARCHAR(13) not null,
    flight_id   INTEGER     not null,
    boarding_no INTEGER     not null,
    seat_no     VARCHAR(4)  not null
);

create table bookings (
    book_ref     VARCHAR(6)               not null,
    book_date    TIMESTAMP WITH TIME ZONE not null,
    total_amount NUMERIC(10,2)            not null
);

create table flights (
    flight_id           INTEGER auto_increment   primary key,
    flight_no           VARCHAR(6)               not null,
    scheduled_departure TIMESTAMP WITH TIME ZONE not null,
    scheduled_arrival   TIMESTAMP WITH TIME ZONE not null,
    departure_airport   VARCHAR(3)               not null,
    arrival_airport     VARCHAR(3)               not null,
    status              VARCHAR(20)              not null,
    aircraft_code       VARCHAR(3)               not null,
    actual_departure    TIMESTAMP WITH TIME ZONE
    actual_arrival      TIMESTAMP WITH TIME ZONE
);

create table seats (
    aircraft_code   VARCHAR(3)  not null,
    seat_no         VARCHAR(4)  not null,
    fare_conditions VARCHAR(10) not null
);

create table ticket_flights (
    ticket_no       VARCHAR(13)   not null,
    flight_id       INTEGER       not null,
    fare_conditions varchar(10)   not null,
    amount          NUMERIC(10,2) not null
);

create table tickets (
    ticket_no      VARCHAR(13)    not null,
    book_ref       VARCHAR(6)     not null,
    passenger_id   VARCHAR(20)    not null,
    passenger_name VARCHAR(1024)  not null,
    contact_data   JSON
);