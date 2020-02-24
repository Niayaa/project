/*Question 6)Produce a table that lists the most popular service (or services).
That is, give the name of the service that has the most subscribers.
*/
drop table if exists countSer;
create table countSer as select services.service
  from services
  inner join service_subscribers on service_subscribers.service == services.scode;
drop table if exists mostPoSer;
create table mostPoSer as select y.service,MAX(y.serNum)
  from (select countSer.service,count(*) as serNum from countSer group by countSer.service)y

/*test output for select * from mostPoSer;
y.service       MAX(y.serNum)
--------------  -------------
Message Answer  25
*/
