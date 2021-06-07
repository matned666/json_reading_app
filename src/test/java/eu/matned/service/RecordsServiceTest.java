package eu.matned.service;

import eu.matned.dto.IRecordDTO;
import eu.matned.dto.RecordDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RecordsServiceTest {

    private final IRecordsService recordsService = new RecordsService();
    private static final String inputFilePath = "src/test/resources/statuses_test.json";
    private static final String outputFilePath = "src/test/resources/records_test.csv";

    private static final String dateFrom = "2017-07-01 00:00:00";
    private static final String dateTo = Timestamp.valueOf(LocalDateTime.now()).toString();


    @Test
    void allRecords() throws IOException {
        assertEquals(recordsService.allRecords(inputFilePath).size(), 6);
    }

    @Test
    void allRecordsBetweenDates() throws IOException {
        assertEquals(recordsService.allRecordsBetweenDates(dateFrom, dateTo, RecordsService.TableFields.KLIENT_ID, true, inputFilePath).size(), 4);

    }

    @Test
    void saveRecordsToFile() throws IOException {
        IRecordDTO record = new RecordDTO.RecordDTOBuilder()
                .kontakt_id(12L)
                .klient_id(12L)
                .pracownik_id(12L)
                .status("zainteresowany")
                .kontakt_ts(Timestamp.valueOf(dateFrom))
                .build();
        recordsService.saveRecordsToFile(Collections.singletonList(record), outputFilePath);

        String expected = "Kontakt_id|Klient_id|Pracownik_id|Status|Kontakt_ts\n" +
                "12|12|12|zainteresowany|2017-07-01 12:00:00.0\n";

        Path fileName = Path.of(outputFilePath);
        String actual = Files.readString(fileName);

        assertEquals(expected, actual);
    }
}