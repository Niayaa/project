/*Question 8)Write an SQL query that would find if the line with phone number
(613) 712-0024 is currently available to take a call (that it is IDLE).
*/
select lines.portid,lines.state from lines
where (areacode == 613) AND (officecode == 712);

/*test output
portid      state
----------  ----------
24          BUSY
so it would be not available
*/
