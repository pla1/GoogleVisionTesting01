package net.pla1.pojo.request;

public class Feature {
    private String type = "TEXT_DETECTION";
    private int maxResults = 1;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
}
