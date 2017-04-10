
package eu.openminted.toolkit.crossref.model.works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("indexed")
    @Expose
    private Indexed indexed;
    @SerializedName("reference-count")
    @Expose
    private Integer referenceCount;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("issue")
    @Expose
    private String issue;
    @SerializedName("license")
    @Expose
    private List<License> license = null;
    @SerializedName("funder")
    @Expose
    private List<Funder> funder = null;
    @SerializedName("content-domain")
    @Expose
    private ContentDomain contentDomain;
    @SerializedName("short-container-title")
    @Expose
    private List<String> shortContainerTitle = null;
    @SerializedName("cited-count")
    @Expose
    private Integer citedCount;
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
    @SerializedName("update-policy")
    @Expose
    private String updatePolicy;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("title")
    @Expose
    private List<String> title = null;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("volume")
    @Expose
    private String volume;
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
    @SerializedName("link")
    @Expose
    private List<Link> link = null;
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
    @SerializedName("alternative-id")
    @Expose
    private List<String> alternativeId = null;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("ISSN")
    @Expose
    private List<String> iSSN = null;
    @SerializedName("citing-count")
    @Expose
    private Integer citingCount;
    @SerializedName("subject")
    @Expose
    private List<String> subject = null;
    @SerializedName("assertion")
    @Expose
    private List<Assertion> assertion = null;

    public Indexed getIndexed() {
        return indexed;
    }

    public void setIndexed(Indexed indexed) {
        this.indexed = indexed;
    }

    public Integer getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(Integer referenceCount) {
        this.referenceCount = referenceCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public List<License> getLicense() {
        return license;
    }

    public void setLicense(List<License> license) {
        this.license = license;
    }

    public List<Funder> getFunder() {
        return funder;
    }

    public void setFunder(List<Funder> funder) {
        this.funder = funder;
    }

    public ContentDomain getContentDomain() {
        return contentDomain;
    }

    public void setContentDomain(ContentDomain contentDomain) {
        this.contentDomain = contentDomain;
    }

    public List<String> getShortContainerTitle() {
        return shortContainerTitle;
    }

    public void setShortContainerTitle(List<String> shortContainerTitle) {
        this.shortContainerTitle = shortContainerTitle;
    }

    public Integer getCitedCount() {
        return citedCount;
    }

    public void setCitedCount(Integer citedCount) {
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

    public String getUpdatePolicy() {
        return updatePolicy;
    }

    public void setUpdatePolicy(String updatePolicy) {
        this.updatePolicy = updatePolicy;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
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

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
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

    public List<String> getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(List<String> alternativeId) {
        this.alternativeId = alternativeId;
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

    public Integer getCitingCount() {
        return citingCount;
    }

    public void setCitingCount(Integer citingCount) {
        this.citingCount = citingCount;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public List<Assertion> getAssertion() {
        return assertion;
    }

    public void setAssertion(List<Assertion> assertion) {
        this.assertion = assertion;
    }

    @Override
    public String toString() {
        return "Message{" + "indexed=" + indexed + ", referenceCount=" + referenceCount + ", publisher=" + publisher + ", issue=" + issue + ", license=" + license + ", funder=" + funder + ", contentDomain=" + contentDomain + ", shortContainerTitle=" + shortContainerTitle + ", citedCount=" + citedCount + ", publishedPrint=" + publishedPrint + ", dOI=" + dOI + ", type=" + type + ", created=" + created + ", page=" + page + ", updatePolicy=" + updatePolicy + ", source=" + source + ", title=" + title + ", prefix=" + prefix + ", volume=" + volume + ", author=" + author + ", member=" + member + ", containerTitle=" + containerTitle + ", originalTitle=" + originalTitle + ", link=" + link + ", deposited=" + deposited + ", score=" + score + ", subtitle=" + subtitle + ", shortTitle=" + shortTitle + ", issued=" + issued + ", alternativeId=" + alternativeId + ", uRL=" + uRL + ", iSSN=" + iSSN + ", citingCount=" + citingCount + ", subject=" + subject + ", assertion=" + assertion + '}';
    }

}
