package net.pla1.pojo.request;


import java.util.ArrayList;

public class AnnotateImageRequest {
    private ArrayList<Request> requests = new ArrayList<Request>();

    public AnnotateImageRequest(Request request) {
        getRequests().add(request);
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }
}
