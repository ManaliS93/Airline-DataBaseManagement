create or replace function getPriceChange (new in number, old in number)
  return number is change_in_cost number;
BEGIN
  change_in_cost := new-old;
  RETURN(change_in_cost);
END;
/