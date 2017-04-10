package eu.openminted.toolkit.crossref.model.member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberResponse {

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

    @Override
    public String toString() {
        return "MemberResponse{" + "status=" + status + ", messageType=" + messageType + ", messageVersion=" + messageVersion + ", message=" + message + '}';
    }

}
