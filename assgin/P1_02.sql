/*Question 2)Produce a table that lists all the area code,office code combinations
and the number of subscribers with that area code, office code combination.
*/
drop table if exists numberofServicecode;
create table numberofServicecode as select area, office from trunk_routes ;
select area||office as codecombination, count(*) as NUM from numberofServicecode group by area||office;

/*the test output:
codecombination  NUM
---------------  ----------
000000           1
416000           2
416219           1
416331           1
416333           1
416334           1
416756           1
514000           1
*/
