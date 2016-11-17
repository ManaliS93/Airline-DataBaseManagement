CREATE OR REPLACE TRIGGER cancelReservation
AFTER INSERT ON CDate
FOR EACH ROW
DECLARE
res_num varchar2(5);
flight_num varchar2(3);
fdate date;
num_tickets_booked number;
aid varchar2(4);
ptype varchar2(4);
pcapacity number;
result_type varchar2(4);
CURSOR c1
IS
  select reservation_number, flight_number, flight_date from Reservation_Detail NATURAL JOIN (select reservation_number from Reservation where ticketed = 'N') where (Reservation_Detail.flight_date-:new.c_date)*24<12;
BEGIN
  OPEN c1;
   LOOP
      FETCH c1 into res_num, flight_num, fdate;
      EXIT WHEN c1%notfound;
        delete from reservation_detail where reservation_number = res_num;
		delete from reservation where reservation_number = res_num;
		select count(*) into num_tickets_booked from reservation_detail where flight_number = flight_num and flight_date = fdate;  
        select airline_id, plane_type into aid, ptype from Flight where flight_number = flight_num;
		select plane_capacity into pcapacity from plane where plane_type = ptype and owner_id = aid;
		result_type := ptype;
		select plane_type into result_type from (select * from plane where owner_id = aid and plane_capacity < pcapacity and plane_capacity >= num_tickets_booked ORDER BY plane_capacity DESC) where rownum = 1;
		IF result_type != ptype THEN
		  update flight set plane_type = result_type where flight_number = flight_num;
		END IF;
   END LOOP;
   CLOSE c1;
END;
/