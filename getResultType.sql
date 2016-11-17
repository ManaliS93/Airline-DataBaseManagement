CREATE OR REPLACE FUNCTION getResultType(aid varchar2, ptype varchar2, tickets_booked number)
  RETURN varchar2 is result_type varchar2(5);
BEGIN
  result_type := ptype;
  select plane_type into result_type from (select * from Plane where plane_capacity > tickets_booked and owner_id = aid ORDER BY plane_capacity) where rownum=1;   
  RETURN(result_type);
END;
/