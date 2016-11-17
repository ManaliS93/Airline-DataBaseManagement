CREATE OR REPLACE TRIGGER planeUpgrade
BEFORE INSERT ON Reservation_Detail
FOR EACH ROW
DECLARE
 flight_capacity number;
 aid varchar2(3);
 ptype varchar2(4);
 tickets_booked number;
 result_type varchar2(4);
BEGIN
  select plane_capacity ,airline_id ,Flight.plane_type into flight_capacity, aid, ptype from Flight INNER JOIN Plane ON Flight.airline_id = Plane.owner_id AND Flight.plane_type = Plane.plane_type
     where Flight.flight_number = :new.flight_number;
  tickets_booked := getBookedTickets(:new.flight_number, :new.flight_date);
  IF tickets_booked >= flight_capacity THEN
    result_type := getResultType(aid, ptype, tickets_booked);
    IF result_type <> ptype THEN
      update Flight set plane_type = result_type where flight_number = :new.flight_number;
	end if;
  end if;
END;
/