
package eu.openminted.toolkit.springer.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publicationName")
    @Expose
    private String publicationName;
    @SerializedName("issn")
    @Expose
    private String issn;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("doi")
    @Expose
    private String doi;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publicationDate")
    @Expose
    private String publicationDate;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("startingPage")
    @Expose
    private String startingPage;
    @SerializedName("endingPage")
    @Expose
    private String endingPage;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartingPage() {
        return startingPage;
    }

    public void setStartingPage(String startingPage) {
        this.startingPage = startingPage;
    }

    public String getEndingPage() {
        return endingPage;
    }

    public void setEndingPage(String endingPage) {
        this.endingPage = endingPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        return "Record{" + "identifier=" + identifier + ", title=" + title + ", publicationName=" + publicationName + ", issn=" + issn + ", isbn=" + isbn + ", doi=" + doi + ", publisher=" + publisher + ", publicationDate=" + publicationDate + ", volume=" + volume + ", number=" + number + ", startingPage=" + startingPage + ", endingPage=" + endingPage + ", url=" + url + ", copyright=" + copyright + '}';
    }

    
}
