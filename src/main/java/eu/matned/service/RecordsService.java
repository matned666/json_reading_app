package eu.matned.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.matned.dto.IRecordDTO;
import eu.matned.dto.JsonStructureDTO;
import eu.matned.dto.RecordDTO;
import eu.matned.model.IRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecordsService implements IRecordsService {


    private final String CSV_SEPARATOR = "|";


    @Override
    public List<IRecordDTO> allRecords(String fileName) throws IOException {
        return getAllRecordsFromJsonFile(fileName);
    }

    @Override
    public List<IRecordDTO> allRecordsBetweenDates(String dateFrom, String dateTo, TableFields sortBy, boolean isAsc,  String fileName) throws IOException {
        List<IRecordDTO> recs = getCollect(dateFrom, dateTo, fileName);

        switch (sortBy){
            case KONTAKT_ID: {
                recs.sort(Comparator.comparing(IRecordDTO::getKontakt_id, isAsc? Comparator.naturalOrder(): Comparator.reverseOrder()));
                break;
            }
            case KLIENT_ID: {
                recs.sort(Comparator.comparing(IRecordDTO::getKlient_id, isAsc? Comparator.naturalOrder(): Comparator.reverseOrder()));
                break;
            }
            case PRACOWNIK_ID: {
                recs.sort(Comparator.comparing(IRecordDTO::getPracownik_id, isAsc? Comparator.naturalOrder(): Comparator.reverseOrder()));
                break;
            }
            case STATUS: {
                recs.sort(Comparator.comparing(IRecordDTO::getStatus, isAsc? Comparator.naturalOrder(): Comparator.reverseOrder()));
                break;
            }
            case KONTAKT_TS: {
                recs.sort(Comparator.comparing(IRecordDTO::getKontakt_ts, isAsc? Comparator.naturalOrder(): Comparator.reverseOrder()));
                break;
            }
        }
        return recs;
    }

    private List<IRecordDTO> getCollect(String dateFrom, String dateTo, String fileName) throws IOException {
        return getAllRecordsFromJsonFile(fileName).stream()
                .filter(x -> IRecord.getFromDTO(x).getKontakt_ts().after(Timestamp.valueOf(dateFrom)))
                .filter(x -> IRecord.getFromDTO(x).getKontakt_ts().before(Timestamp.valueOf(dateTo)))
                .collect(Collectors.toList());
    }


    @Override
    public void saveRecordsToFile(List<IRecordDTO> records, String outputFilePath) {

        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8));
            bw.write(getHeaderCSV());
            bw.newLine();
            for (IRecordDTO record : records)
            {
                bw.write(getRecordsToCSV(IRecord.getFromDTO(record)));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ignored) {}
    }

    private List<IRecordDTO> getAllRecordsFromJsonFile(String fileName) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        JsonStructureDTO structure = objectMapper.readValue(Files.readString(Path.of(fileName)), JsonStructureDTO.class);
        return structure.getRecords().stream().map(this::mapper).collect(Collectors.toList());
    }

    private IRecordDTO mapper(RecordDTO rec){
        return rec;
    }

    private String getRecordsToCSV(IRecord record) {
        StringBuilder oneLine = new StringBuilder();
        oneLine.append(record.getKontakt_id());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(record.getKlient_id());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(record.getPracownik_id());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(record.getStatus());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(record.getKontakt_ts());
        return oneLine.toString();
    }

    private String getHeaderCSV() {
        StringBuilder line = new StringBuilder();
        line.append("Kontakt_id");
        line.append(CSV_SEPARATOR);
        line.append("Klient_id");
        line.append(CSV_SEPARATOR);
        line.append("Pracownik_id");
        line.append(CSV_SEPARATOR);
        line.append("Status");
        line.append(CSV_SEPARATOR);
        line.append("Kontakt_ts");
        return line.toString();
    }

    public enum TableFields{
        KONTAKT_ID,
        KLIENT_ID,
        PRACOWNIK_ID,
        STATUS,
        KONTAKT_TS
    }
}
