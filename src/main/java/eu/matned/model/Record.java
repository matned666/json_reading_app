package eu.matned.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Record implements IRecord {

    private final Long kontakt_id;
    private final Long klient_id;
    private final Long pracownik_id;
    private final String status;
    private final Timestamp kontakt_ts;


    private Record(RecordBuilder builder) {
        this.kontakt_id = builder.kontakt_id;
        this.klient_id = builder.klient_id;
        this.pracownik_id = builder.pracownik_id;
        this.status = builder.status;
        this.kontakt_ts = builder.kontakt_ts;
    }

    @Override
    public Long getKontakt_id() {
        return kontakt_id;
    }

    @Override
    public Long getKlient_id() {
        return klient_id;
    }

    @Override
    public Long getPracownik_id() {
        return pracownik_id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public Timestamp getKontakt_ts() {
        return kontakt_ts;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record1 = (Record) o;
        return Objects.equals(kontakt_id, record1.kontakt_id) && Objects.equals(klient_id, record1.klient_id) && Objects.equals(pracownik_id, record1.pracownik_id) && Objects.equals(status, record1.status) && Objects.equals(kontakt_ts, record1.kontakt_ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kontakt_id, klient_id, pracownik_id, status, kontakt_ts);
    }

    @Override
    public String toString() {
        return  "kontakt_id=" + kontakt_id +
                ", klient_id=" + klient_id +
                ", pracownik_id=" + pracownik_id +
                ", status='" + status + '\'' +
                ", kontakt_ts=" + kontakt_ts +
                "/n";
    }

    public static class RecordBuilder {
        private Long kontakt_id;
        private Long klient_id;
        private Long pracownik_id;
        private String status;
        private Timestamp kontakt_ts;


        public RecordBuilder() {
        }

        public RecordBuilder kontakt_id(Long kontakt_id){
            this.kontakt_id = kontakt_id;
            return this;
        }

        public RecordBuilder klient_id(Long klient_id){
            this.klient_id = klient_id;
            return this;
        }

        public RecordBuilder pracownik_id(Long pracownik_id){
            this.pracownik_id = pracownik_id;
            return this;
        }

        public RecordBuilder status(String status){
            this.status = status;
            return this;
        }

        public RecordBuilder kontakt_ts(String kontakt_ts){
            this.kontakt_ts = Timestamp.valueOf(kontakt_ts);
            return this;
        }

        public Record build(){
            return new Record(this);
        }


    }


}
