package eu.openminted.crossref.model_tmp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultipleWorksResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message-type")
    @Expose
    private String messageType;
    @SerializedName("message-version")
    @Expose
    private String messageVersion;
    @SerializedName("message")
    @Expose
    private Message message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
