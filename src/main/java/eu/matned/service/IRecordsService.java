package eu.matned.service;

import eu.matned.dto.IRecordDTO;

import java.io.IOException;
import java.util.List;

public interface IRecordsService {

    List<IRecordDTO> allRecords(String fileName) throws IOException;
    List<IRecordDTO> allRecordsBetweenDates(String dateFrom, String dateTo, RecordsService.TableFields field, boolean isAsc, String fileName) throws IOException;
    void saveRecordsToFile(List<IRecordDTO> records, String outputFilePath) throws IOException;

}
