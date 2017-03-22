package eu.openminted.crossref.model.member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prefix {

    @SerializedName("public-references")
    @Expose
    private Boolean publicReferences;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;

    public Boolean getPublicReferences() {
        return publicReferences;
    }

    public void setPublicReferences(Boolean publicReferences) {
        this.publicReferences = publicReferences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Prefix{" + "publicReferences=" + publicReferences + ", name=" + name + ", value=" + value + '}';
    }

}
