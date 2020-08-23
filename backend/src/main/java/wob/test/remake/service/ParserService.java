package wob.test.remake.service;

import org.json.JSONArray;

import java.io.IOException;
import java.io.Reader;

public interface ParserService {
    String readAll(Reader reader) throws IOException;
    JSONArray readJsonFromUrl(String url) throws IOException;
}
