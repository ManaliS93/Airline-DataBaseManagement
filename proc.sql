CREATE or replace PROCEDURE getBookedTicketsProcedure (input_flight_num IN varchar2 , input_flight_date IN date,num_tickets_booked OUT Number) AS
   
   BEGIN
      select count(*) into num_tickets_booked from Reservation_detail where flight_number = input_flight_num and flight_date = input_flight_date;
   END;
/