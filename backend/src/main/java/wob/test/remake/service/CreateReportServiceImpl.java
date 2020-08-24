package wob.test.remake.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wob.test.remake.entity.Report;
import wob.test.remake.repository.ReportRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class CreateReportServiceImpl implements CreateReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public CreateReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public JSONObject createReport() throws ParseException {
        JSONObject result = new JSONObject();

        Report report = reportRepository.findAll().get(0);

        result.put("total_listings", report.getTotalListings());

        result.put("total_ebay_listings", report.getTotalEbayListings());
        result.put("total_ebay_listing_price", report.getTotalEbayListingPrice());
        result.put("avg_ebay_listing_price", report.getAvgEbayListingPrice());

        result.put("total_amazon_listings", report.getTotalAmazonListings());
        result.put("total_amazon_listing_price", report.getTotalAmazonListingPrice());
        result.put("avg_amazon_listing_price", report.getAvgAmazonListingPrice());

        result.put("best_lister", report.getBestLister());
        result.put("best_lister_listings", report.getBestListerListings());

        result.put("monthly_results", createMonthlyReport(report.getOldestListing(), report.getNewestListing()));

        return result;
    }

    private JSONArray createMonthlyReport(Date oldestListing, Date newestListing) throws ParseException {
        JSONArray monthlyResult = new JSONArray();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar firstListing = Calendar.getInstance();
        Calendar lastListing = Calendar.getInstance();
        firstListing.setTime(dateFormat.parse(oldestListing.toString()));
        lastListing.setTime(dateFormat.parse(newestListing.toString()));

        int year;
        int month;
        JSONObject tmpObject;

        while(firstListing.before(lastListing)){
            year = firstListing.get(Calendar.YEAR);
            month = firstListing.get(Calendar.MONTH);

            tmpObject = getResultByMonth(year, month);

            monthlyResult.put(tmpObject);

            firstListing.add(Calendar.MONTH, 1);
        }

        return monthlyResult;
    }

    private JSONObject getResultByMonth(int year, int month) {
        JSONObject thisMonthResult = new JSONObject();
        Report thisMonthReport = reportRepository.getReportByDate(year, month);

        thisMonthResult.put("month", year + "/" + month);

        thisMonthResult.put("total_listings", thisMonthReport.getTotalListings());

        thisMonthResult.put("total_ebay_listings", thisMonthReport.getTotalEbayListings());
        thisMonthResult.put("total_ebay_listing_price", thisMonthReport.getTotalEbayListingPrice());
        thisMonthResult.put("avg_ebay_listing_price", thisMonthReport.getAvgEbayListingPrice());

        thisMonthResult.put("total_amazon_listings", thisMonthReport.getTotalAmazonListings());
        thisMonthResult.put("total_amazon_listing_price", thisMonthReport.getTotalAmazonListingPrice());
        thisMonthResult.put("avg_amazon_listing_price", thisMonthReport.getAvgAmazonListingPrice());

        thisMonthResult.put("best_lister", thisMonthReport.getBestLister());
        thisMonthResult.put("best_lister_listings", thisMonthReport.getBestListerListings());

        return thisMonthResult;
    }

    @Override
    public void saveReport(JSONObject report) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("report.json", false)));
        writer.println(report.toString());
        writer.close();
    }
}
