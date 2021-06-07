package eu.matned.model;

import eu.matned.dto.IRecordDTO;

import java.sql.Timestamp;

public interface IRecord {

    Long getKontakt_id();

    Long getKlient_id();

    Long getPracownik_id();

    String getStatus();

    Timestamp getKontakt_ts();

    static IRecord getFromDTO(IRecordDTO dto) {
        if (dto != null)
            return new Record.RecordBuilder()
                    .kontakt_id(dto.getKontakt_id())
                    .klient_id(dto.getKlient_id())
                    .pracownik_id(dto.getPracownik_id())
                    .status(dto.getStatus())
                    .kontakt_ts(dto.getKontakt_ts())
                    .build();
        else return null;
    }


}
