/*Question 5)Find the names of all the subscribers
who subscribe to at least three services.
*/
select name,address,alt.serNum as serNum
from(select name,address, count(*) as serNum from subWithSer group by name||address)alt
where alt.serNum >= 3;

/*the test output
name           address       serNum
-------------  ------------  ----------
Chris Pronger  48 16th Ave.  3
Frank Thomas   14 Hull Ave.  3
Homer Simpson  123 Prince o  3
Joe Carter     18 Summerset  3
Matt Stajan    50 LakeShore  3
Michael Jorda  1223 Carling  3
Steve Sampras  448 St Clare  3
Vince Carter   45 Hunt Club  3
*/
