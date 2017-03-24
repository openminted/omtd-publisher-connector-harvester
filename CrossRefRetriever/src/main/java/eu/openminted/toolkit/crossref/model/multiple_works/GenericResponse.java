package eu.openminted.toolkit.crossref.model.multiple_works;

//package eu.openminted.crossref.model_tmp;
//
//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;
//
///**
// *
// * @author lucasanastasiou
// */
//public class GenericResponse {
//
//    String status;
//    @SerializedName(value = "message-type")
//    String message_type;
//    @SerializedName(value = "message-version")
//    String message_version;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage_type() {
//        return message_type;
//    }
//
//    public void setMessage_type(String message_type) {
//        this.message_type = message_type;
//    }
//
//    public String getMessage_version() {
//        return message_version;
//    }
//
//    public void setMessage_version(String message_version) {
//        this.message_version = message_version;
//    }
//
//    @Override
//    public String toString() {
//        return "GenericResponse{" + "status=" + status + ", message_type=" + message_type + ", message_version=" + message_version + '}';
//    }
// 
//    
//    public static void main(String[] args){
//        GenericResponse genericResponse = new GenericResponse();
//        genericResponse.setStatus("ok");
//        genericResponse.setMessage_type("member-list");
//        genericResponse.setMessage_version("1.0.0");
//        
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(genericResponse);
//        System.out.println("jsonString = " + jsonString);
//    }
//}
