/*Question 3)List the names of all the subscribers who are originators of a call
to someone who is also a subscriber on the same switch (i.e. a line to line call))
*/
drop table if exists origSubWithSameWSub;
create table origSubWithSameWSub as select calls.orig,subscribers.name,calls.area||office||stn as servicenumber
  from calls,subscribers
  where calls.orig == subscribers.portid;
select origSubWithSameWSub.name
from origSubWithSameWSub
inner join subscriberInfo on origSubWithSameWSub.servicenumber == subscriberInfo.phonenumber;

/*test output
name
-----------
Mats Sundin
Jason Allis
Homer Simps
Michael Jor
Ed Belfour
Jack Morris
Mario Lemie
Vince Carte
Bryan MacCa
Homer Simps
Eric Staal
*/
/* by the way the subscriber who are in same switch would be
name         name
-----------  ----------
Mats Sundin  Steve Nash
Jason Allis  Gordie How
Homer Simps  Mario Lemi
Michael Jor  Roberto Al
Ed Belfour   Bobby Hull
Jack Morris  Sidney Cro
Mario Lemie  Mark Messi
Vince Carte  Rob Blake
Bryan MacCa  Gary Rober
Homer Simps  Chris Pron
Eric Staal   Frank Thom
*/
