/*
Question 1)Produce a table consisting of the names and addresses
of the subscribers and their phone numbers.
*/
/*SELECT name, address INTO subscriberInfo from subscriber;*/
drop table if exists subscriberInfo;
create table subscriberInfo as select subscribers.name, subscribers.address, lines.areacode||officecode||stationcode as phonenumber
  from subscribers, lines where subscribers.portid == lines.portid;

/*test output:
name                  address               phonenumber
--------------------  --------------------  --------------------
Mats Sundin           45 Elgin St.          6131340001
Jason Allison         46 Elgin St.          6131360002
Eric Lindros          48 Elgin St.          6131560003
Bryan MacCabe         23 MacLeod St.        6132200004
Steve Nash            1129 Otterson Dr.     6132210005
Michael Jordan        1223 Carling Ave.     6132220006
*/
