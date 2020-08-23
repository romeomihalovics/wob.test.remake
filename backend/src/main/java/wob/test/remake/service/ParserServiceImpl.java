package wob.test.remake.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ParserServiceImpl implements ParserService {

    @Override
    public String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int inputLine;
        while((inputLine = reader.read()) != -1) {
            stringBuilder.append((char) inputLine);
        }
        return stringBuilder.toString();
    }

    @Override
    public JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(bufferedReader);
            return new JSONArray(jsonText);
        }
    }
}
