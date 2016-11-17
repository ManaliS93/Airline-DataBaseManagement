CREATE OR REPLACE TRIGGER adjustTicket
before update of high_price, low_price on Price 
for each row
begin
  update Reservation set cost = cost+getPriceChange(:new.high_price, :old.high_price) where start_city=:new.departure_city and end_city=:new.arrival_city and cost=:old.high_price and ticketed='N'; 
  update Reservation set cost = cost+getPriceChange(:new.low_price, :old.low_price) where start_city=:new.departure_city and end_city=:new.arrival_city and cost=:old.low_price and ticketed = 'N';
end;
/

--//check whether both needs to be changed for this trigger to be triggered or only one is enough

