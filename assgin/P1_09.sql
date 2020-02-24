/*Question 9)Do the same as question 1.7 but this time only include trunks that
have at least one 'IDLE' channel. That is, write an SQL query that will produce
in one table a list of all the acceptable trunks that can be used to route a call
to the 416 area code, office code 334 have at least one idle channel. This query
should list the trunks in the order of preference.
(The answer should list trunks routes 416,334 then 416,000 then 000,000 for example)
*/
select distinct portid, foreign_switch,area,office from trunks
natural join trunk_routes natural join trunk_channels
where (area ='334' or area='000') and (office='416'or office='000') and state='IDLE'
order by area desc, office desc;

/* test output
portid      foreign_switch  area        office
----------  --------------  ----------  ----------
107         Innes           000         000
*/
