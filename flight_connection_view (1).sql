DROP VIEW Flight_connection;
CREATE VIEW Flight_connection AS SELECT distinct f1.flight_number as flight1, f2.flight_number as flight2,f1.departure_city as city1,f1.arival_city as city2,f2.arival_city as city3 FROM Flight f1, Flight f2 where f1.arival_city = f2.departure_city;
