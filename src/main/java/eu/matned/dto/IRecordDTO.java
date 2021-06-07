package eu.matned.dto;

import eu.matned.model.IRecord;

public interface IRecordDTO {


    Long getKontakt_id();

    Long getKlient_id();

    Long getPracownik_id();

    String getStatus();

    String getKontakt_ts();

    static IRecordDTO getFromModel(IRecord model) {
        if (model != null)
            return new RecordDTO.RecordDTOBuilder()
                    .kontakt_id(model.getKontakt_id())
                    .klient_id(model.getKlient_id())
                    .pracownik_id(model.getPracownik_id())
                    .status(model.getStatus())
                    .kontakt_ts(model.getKontakt_ts())
                    .build();
        else return null;
    }

}
