package wob.test.remake.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

public interface CreateReportService {
    JSONObject createReport() throws ParseException, IOException;
    void saveReport(JSONObject report) throws IOException;
}
