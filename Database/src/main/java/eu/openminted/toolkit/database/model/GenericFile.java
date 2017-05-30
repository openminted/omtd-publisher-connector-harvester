package eu.openminted.toolkit.database.model;

import java.sql.Timestamp;

/**
 *
 * @author lucasanastasiou
 */
public class GenericFile {

    long id;
    String publisher_prefix;
    String doi;
    String metadata;
    String metadata_filename;
    String pdf_filename;
    Timestamp time_accessed;

    public GenericFile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublisher_prefix() {
        return publisher_prefix;
    }

    public void setPublisher_prefix(String publisher_prefix) {
        this.publisher_prefix = publisher_prefix;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getMetadata_filename() {
        return metadata_filename;
    }

    public void setMetadata_filename(String metadata_filename) {
        this.metadata_filename = metadata_filename;
    }

    public String getPdf_filename() {
        return pdf_filename;
    }

    public void setPdf_filename(String pdf_filename) {
        this.pdf_filename = pdf_filename;
    }

    public Timestamp getTime_accessed() {
        return time_accessed;
    }

    public void setTime_accessed(Timestamp time_accessed) {
        this.time_accessed = time_accessed;
    }
    
}
