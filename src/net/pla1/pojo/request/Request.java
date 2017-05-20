package net.pla1.pojo.request;

import java.util.ArrayList;

public class Request {
    private Image image = new Image();
    private ArrayList<Feature> features = new ArrayList<Feature>();

    public Request(String base64Image) {
        image.setContent(base64Image);
        features.add(new Feature());
    }
}
