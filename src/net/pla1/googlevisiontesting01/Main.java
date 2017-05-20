package net.pla1.googlevisiontesting01;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.pla1.pojo.request.AnnotateImageRequest;
import net.pla1.pojo.request.Request;
import net.pla1.pojo.response.AnnotateImageResponse;
import net.pla1.pojo.response.Response;
import net.pla1.pojo.response.TextAnnotation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    private final static String URL_GOOGLE_VISION_API = "https://vision.googleapis.com/v1/images:annotate?key=";

    public Main() {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.annotateImage();
    }

    private void annotateImage() throws Exception {
        StopWatch stopWatch = new StopWatch();
        Properties properties = new Properties();
        properties.load(new FileInputStream("/etc/net.pla1.googlevisiontesting01.Main.properties"));
        File file = new File("/tmp/screenshot.png");
        byte[] bytes = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        System.out.format("Image string length: %d.\n", new String(bytes).length());
        Request request = new Request(new String(bytes));
        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(annotateImageRequest);
        System.out.println(json);
        URL url = new URL(String.format("%s%s", URL_GOOGLE_VISION_API, properties.get("api_key")));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(json.length()));
        conn.setUseCaches(false);
        try (DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream())) {
            dataOutputStream.write(json.getBytes());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder output = new StringBuilder();
        String line = "";
        while (line != null) {
            output.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        System.out.println(output.toString());
        System.out.println(stopWatch);
        AnnotateImageResponse annotateImageResponse = gson.fromJson(output.toString(), AnnotateImageResponse.class);
        Response air = annotateImageResponse.getResponses().get(0);
        List<TextAnnotation> textAnnotations = air.getTextAnnotations();
        for (TextAnnotation textAnnotation:textAnnotations) {
            System.out.format("%s\n", textAnnotation.getDescription());
        }
        System.out.format("Response quantity: %d  Text annotation quantity: %d.\n", annotateImageResponse.getResponses().size(), annotateImageResponse.getResponses().get(0).getTextAnnotations().size());
    }
}
