package eu.matned;

import eu.matned.dto.IRecordDTO;
import eu.matned.service.IRecordsService;
import eu.matned.service.RecordsService;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final String inputFilePath = "src/main/resources/statuses.json";
    private static final String outputFilePath = "src/main/resources/records.csv";


//    Dane do wyniku
    private static final String dateFrom = "2017-07-01 00:00:00";
    private static final String dateTo = Timestamp.valueOf(LocalDateTime.now()).toString();
    private static final RecordsService.TableFields sortByField = RecordsService.TableFields.KLIENT_ID;
    private static final boolean isAsc = true;

    public static void main(String[] args) throws IOException {
/*
 ZADANIE 1
 */

        IRecordsService recordsService = new RecordsService();
        List<IRecordDTO> recs =  recordsService.allRecordsBetweenDates(dateFrom, dateTo, sortByField, isAsc,inputFilePath);
        recordsService.saveRecordsToFile(recs, outputFilePath);

/*
ZADANIE 2

      SELECT r.klient_id, count(r.klient_id) FROM record r group by r.klient_id having count(r.klient_id) >= 3;

*/

/*
ZADANIE 3

 WITH status AS (
  SELECT DISTINCT klient_id,
    first_value(status_kontaktu) OVER w1 AS last_status,
    nth_value(status_kontaktu, 2) OVER w1 AS prev_status
  FROM record
  WINDOW w1 AS (PARTITION BY klient_id ORDER BY kontakt_ts DESC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)
)
SELECT CURRENT_DATE(),
  SUM(last_status = 'zainteresowany') AS sukcesy,
  SUM(last_status = 'niezainteresowany') AS utraty,
  SUM(last_status = 'poczta_głosowa' OR last_status = 'nie_ma_w_domu') AS do_ponowienia,
  SUM(last_status = 'niezainteresowany' AND prev_status = 'zainteresowany') AS zainteresowani_utraty,
  SUM(last_status = 'zainteresowany' AND prev_status = 'niezainteresowany') AS niezainteresowani_sukcesy
FROM status;

*/


    }
}
