package eu.matned.dto;

import java.util.List;

public class JsonStructureDTO implements IJsonStructure{

    private Integer next_offset;
    private List<RecordDTO> records;

    public JsonStructureDTO() {
    }

    public Integer getNext_offset() {
        return next_offset;
    }

    public List<RecordDTO> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "JsonStructureDTO{" +
                "next_offset=" + next_offset +
                ", records=" + records +
                '}';
    }
}
