
package eu.openminted.toolkit.crossref.model.multiple_works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.openminted.toolkit.crossref.model.works.Link;

public class Item {

    @SerializedName("indexed")
    @Expose
    private Indexed indexed;
    @SerializedName("reference-count")
    @Expose
    private Long referenceCount;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("license")
    @Expose
    private List<License> license = null;
    @SerializedName("content-domain")
    @Expose
    private ContentDomain contentDomain;
    @SerializedName("short-container-title")
    @Expose
    private List<Object> shortContainerTitle = null;
    @SerializedName("cited-count")
    @Expose
    private Long citedCount;
    @SerializedName("published-print")
    @Expose
    private PublishedPrint publishedPrint;
    @SerializedName("DOI")
    @Expose
    private String dOI;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created")
    @Expose
    private Created created;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("title")
    @Expose
    private List<String> title = null;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("author")
    @Expose
    private List<Author> author = null;
    @SerializedName("member")
    @Expose
    private String member;
    @SerializedName("container-title")
    @Expose
    private List<String> containerTitle = null;
    @SerializedName("original-title")
    @Expose
    private List<Object> originalTitle = null;
    @SerializedName("deposited")
    @Expose
    private Deposited deposited;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("subtitle")
    @Expose
    private List<Object> subtitle = null;
    @SerializedName("short-title")
    @Expose
    private List<Object> shortTitle = null;
    @SerializedName("issued")
    @Expose
    private Issued issued;
    @SerializedName("ISBN")
    @Expose
    private List<String> iSBN = null;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("ISSN")
    @Expose
    private List<String> iSSN = null;
    @SerializedName("issn-type")
    @Expose
    private List<IssnType> issnType = null;
    @SerializedName("citing-count")
    @Expose
    private Long citingCount;
    @SerializedName("link")
    @Expose
    private List<Link> link = null;

    public Indexed getIndexed() {
        return indexed;
    }

    public void setIndexed(Indexed indexed) {
        this.indexed = indexed;
    }

    public Long getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(Long referenceCount) {
        this.referenceCount = referenceCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<License> getLicense() {
        return license;
    }

    public void setLicense(List<License> license) {
        this.license = license;
    }

    public ContentDomain getContentDomain() {
        return contentDomain;
    }

    public void setContentDomain(ContentDomain contentDomain) {
        this.contentDomain = contentDomain;
    }

    public List<Object> getShortContainerTitle() {
        return shortContainerTitle;
    }

    public void setShortContainerTitle(List<Object> shortContainerTitle) {
        this.shortContainerTitle = shortContainerTitle;
    }

    public Long getCitedCount() {
        return citedCount;
    }

    public void setCitedCount(Long citedCount) {
        this.citedCount = citedCount;
    }

    public PublishedPrint getPublishedPrint() {
        return publishedPrint;
    }

    public void setPublishedPrint(PublishedPrint publishedPrint) {
        this.publishedPrint = publishedPrint;
    }

    public String getDOI() {
        return dOI;
    }

    public void setDOI(String dOI) {
        this.dOI = dOI;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Created getCreated() {
        return created;
    }

    public void setCreated(Created created) {
        this.created = created;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public List<String> getContainerTitle() {
        return containerTitle;
    }

    public void setContainerTitle(List<String> containerTitle) {
        this.containerTitle = containerTitle;
    }

    public List<Object> getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(List<Object> originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Deposited getDeposited() {
        return deposited;
    }

    public void setDeposited(Deposited deposited) {
        this.deposited = deposited;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Object> getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(List<Object> subtitle) {
        this.subtitle = subtitle;
    }

    public List<Object> getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(List<Object> shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Issued getIssued() {
        return issued;
    }

    public void setIssued(Issued issued) {
        this.issued = issued;
    }

    public List<String> getISBN() {
        return iSBN;
    }

    public void setISBN(List<String> iSBN) {
        this.iSBN = iSBN;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public List<String> getISSN() {
        return iSSN;
    }

    public void setISSN(List<String> iSSN) {
        this.iSSN = iSSN;
    }

    public List<IssnType> getIssnType() {
        return issnType;
    }

    public void setIssnType(List<IssnType> issnType) {
        this.issnType = issnType;
    }

    public Long getCitingCount() {
        return citingCount;
    }

    public void setCitingCount(Long citingCount) {
        this.citingCount = citingCount;
    }

    public String getdOI() {
        return dOI;
    }

    public void setdOI(String dOI) {
        this.dOI = dOI;
    }

    public List<String> getiSBN() {
        return iSBN;
    }

    public void setiSBN(List<String> iSBN) {
        this.iSBN = iSBN;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    public List<String> getiSSN() {
        return iSSN;
    }

    public void setiSSN(List<String> iSSN) {
        this.iSSN = iSSN;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

}
