package services;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class AiTextGenYandex {
    public static String getResponse(@NotNull String inputText) throws IOException {
        URL url = new URL("https://zeapi.yandex.net/lab/api/yalm/text3");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        String query = "{\"query\": \"" + inputText + "\", \"intro\": " + 4 + ", \"filter\": 1}";
        byte[] out = query.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json;");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        StringWriter in = new StringWriter();
        while ((line = reader.readLine()) != null) {
            in.append(line);
        }
        String response = in.toString();
        try {
            response = response.split("text")[1];
            response = response.substring(3, response.length()-2);
            response = StringEscapeUtils.unescapeJava(response);
            //response = Arrays.stream(response.split("\n")).max(Comparator.comparingInt(String::length)).get();
            response = response.split("\n")[0]; // first line
            response = response.split(" - ")[0].trim();
            response = response.split(" -")[0];
        } catch (Exception e) {
            return "";
        }
        return response;
    }
}
