create or replace function getBookedTickets(input_flight_num varchar2, input_flight_date date)
  return number is num_tickets_booked number;
BEGIN
  select count(*) into num_tickets_booked from Reservation_detail where flight_number = input_flight_num and flight_date = input_flight_date;
  RETURN(num_tickets_booked);
END;
/