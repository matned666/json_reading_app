# TASK 1

Simple test web app including 1 module for status call center report<br>
The report is taken from a given statuses.json file.<br>
Later it is converted into a .csv format in a separate records.csv file<br>
The given list can be ordered by any field desc and asc<br>
there can be chosen records where Kontakt_ts is between dates.

--------------------------
# TASK 2

SQL Query:

>SELECT r.klient_id, count(r.klient_id) FROM record r group by r.klient_id having count(r.klient_id) >= 3;
      



------------------------------
#TASK 3 with 2 bonus adds included

>WITH status AS (<br>
SELECT DISTINCT klient_id,<br>
first_value(status_kontaktu) OVER w1 AS last_status,<br>
nth_value(status_kontaktu, 2) OVER w1 AS prev_status<br>
FROM record<br>
WINDOW w1 AS (PARTITION BY klient_id ORDER BY kontakt_ts DESC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)<br>
)<br>
SELECT CURRENT_DATE(),<br>
SUM(last_status = 'zainteresowany') AS sukcesy,<br>
SUM(last_status = 'niezainteresowany') AS utraty,<br>
SUM(last_status = 'poczta_głosowa' OR last_status = 'nie_ma_w_domu') AS do_ponowienia,<br>
SUM(last_status = 'niezainteresowany' AND prev_status = 'zainteresowany') AS zainteresowani_utraty,<br>
SUM(last_status = 'zainteresowany' AND prev_status = 'niezainteresowany') AS niezainteresowani_sukcesy<br>
FROM status;<br>



------------------------------
#Tests


--------------------------

# Made by:

Mateusz N.