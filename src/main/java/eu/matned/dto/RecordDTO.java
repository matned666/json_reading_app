package eu.matned.dto;

import eu.matned.utils.Pattern;

import java.sql.Timestamp;
import java.util.Objects;

public class RecordDTO implements IRecordDTO{

    private Long kontakt_id;
    private Long klient_id;
    private Long pracownik_id;
    private String status;
    private String kontakt_ts;

    public RecordDTO() {
    }

    private RecordDTO(RecordDTOBuilder builder) {
        this.kontakt_id = builder.kontakt_id;
        this.klient_id = builder.klient_id;
        this.pracownik_id = builder.pracownik_id;
        this.status = builder.status;
        this.kontakt_ts = builder.kontakt_ts;
    }

    public Long getKontakt_id() {
        return kontakt_id;
    }

    public Long getKlient_id() {
        return klient_id;
    }

    public Long getPracownik_id() {
        return pracownik_id;
    }

    public String getStatus() {
        return status;
    }

    public String getKontakt_ts() {
        return kontakt_ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDTO recordDTO = (RecordDTO) o;
        return Objects.equals(kontakt_id, recordDTO.kontakt_id) && Objects.equals(klient_id, recordDTO.klient_id) && Objects.equals(pracownik_id, recordDTO.pracownik_id) && Objects.equals(status, recordDTO.status) && Objects.equals(kontakt_ts, recordDTO.kontakt_ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kontakt_id, klient_id, pracownik_id, status, kontakt_ts);
    }

    @Override
    public String toString() {
        return  "{ kontakt_id=" + kontakt_id +
                ", klient_id=" + klient_id +
                ", pracownik_id=" + pracownik_id +
                ", status='" + status + '\'' +
                ", kontakt_ts=" + kontakt_ts +
                " }\n";
    }

    public static class RecordDTOBuilder{
        private Long kontakt_id;
        private Long klient_id;
        private Long pracownik_id;
        private String status;
        private String kontakt_ts;

        public RecordDTOBuilder() {
        }

        public RecordDTOBuilder kontakt_id(Long kontakt_id){
            this.kontakt_id = kontakt_id;
            return this;
        }

        public RecordDTOBuilder klient_id(Long klient_id){
            this.klient_id = klient_id;
            return this;
        }

        public RecordDTOBuilder pracownik_id(Long pracownik_id){
            this.pracownik_id = pracownik_id;
            return this;
        }

        public RecordDTOBuilder status(String status){
            this.status = status;
            return this;
        }

        public RecordDTOBuilder kontakt_ts(Timestamp kontakt_ts){
            this.kontakt_ts = kontakt_ts.toLocalDateTime().format(Pattern.formatter);
            return this;
        }

        public IRecordDTO build(){
            return new RecordDTO(this);
        }
    }
}
