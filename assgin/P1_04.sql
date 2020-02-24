/*Question 4)Find the names and address of all subscribers who subscribe
to all of the available services. (Note the result for the current data might be empty
but your query should work if the TA's add more data to the databse.)
*/
drop table if exists subWithSer;
create table subWithSer as select service_subscribers.service, subscribers.name, subscribers.address
  from service_subscribers, subscribers
  where service_subscribers.line == subscribers.portid;
select max.name,max.address, MAX(max.serNum) as serNum
from(select name,address, count(*) as serNum from subWithSer group by name||address)max
where max.serNum == 5;

/*the test output
name        address     serNum
----------  ----------  ----------

because there are not have the subscriber who service all of service,
but if we add a new subscriber, it would be shown on the list
*/
