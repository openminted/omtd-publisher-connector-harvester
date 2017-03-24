package eu.openminted.toolkit.crossref.model.member;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("last-status-check-time")
    @Expose
    private Long lastStatusCheckTime;
    @SerializedName("primary-name")
    @Expose
    private String primaryName;
    @SerializedName("counts")
    @Expose
    private Counts counts;
    @SerializedName("breakdowns")
    @Expose
    private Breakdowns breakdowns;
    @SerializedName("prefixes")
    @Expose
    private List<String> prefixes = null;
    @SerializedName("coverage")
    @Expose
    private Coverage coverage;
    @SerializedName("prefix")
    @Expose
    private List<Prefix> prefix = null;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("tokens")
    @Expose
    private List<String> tokens = null;
    @SerializedName("flags")
    @Expose
    private Flags flags;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("names")
    @Expose
    private List<String> names = null;

    public Long getLastStatusCheckTime() {
        return lastStatusCheckTime;
    }

    public void setLastStatusCheckTime(Long lastStatusCheckTime) {
        this.lastStatusCheckTime = lastStatusCheckTime;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    public Breakdowns getBreakdowns() {
        return breakdowns;
    }

    public void setBreakdowns(Breakdowns breakdowns) {
        this.breakdowns = breakdowns;
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(List<String> prefixes) {
        this.prefixes = prefixes;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public List<Prefix> getPrefix() {
        return prefix;
    }

    public void setPrefix(List<Prefix> prefix) {
        this.prefix = prefix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

}
