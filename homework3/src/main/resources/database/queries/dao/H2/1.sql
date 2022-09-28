select city, airport_code from airports where
    city in (
        select city from airports
        group by city
        having count(airport_code) > 1)
