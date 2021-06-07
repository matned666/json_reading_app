package eu.matned.dto;

import java.util.List;

public interface IJsonStructure {

    public Integer getNext_offset();

    public List<RecordDTO> getRecords();
}
