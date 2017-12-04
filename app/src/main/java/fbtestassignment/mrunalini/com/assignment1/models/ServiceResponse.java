package fbtestassignment.mrunalini.com.assignment1.models;

import java.util.ArrayList;

/**
 * Created by mrunalinikoritala on 12/3/17.
 */

public class ServiceResponse {

    private String message;
    private RequestModel request;
    private ArrayList<ResponseModel> response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestModel getRequest() {
        return request;
    }

    public void setRequest(RequestModel request) {
        this.request = request;
    }

    public ArrayList<ResponseModel> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<ResponseModel> response) {
        this.response = response;
    }

}
