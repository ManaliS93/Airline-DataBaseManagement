create or replace procedure getPriceChangeProc (new in number, old in number, change_in_cost out number)
IS
BEGIN
  change_in_cost := new-old;
END;
/