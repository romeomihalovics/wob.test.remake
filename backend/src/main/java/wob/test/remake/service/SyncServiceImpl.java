package wob.test.remake.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wob.test.remake.WobTestRemakeApplication;
import wob.test.remake.dto.ListingDTO;
import wob.test.remake.dto.ListingStatusDTO;
import wob.test.remake.dto.LocationDTO;
import wob.test.remake.dto.MarketplaceDTO;

import java.util.List;

@Service
public class SyncServiceImpl implements SyncService {

    private final WobTestRemakeApplication wobTestRemakeApplication;
    private final ParserService parserService;
    private final InvalidLoggerService invalidLoggerService;
    private final FtpService ftpService;
    private final DataUploaderService dataUploaderService;
    private final ValidatorService validatorService;
    private final CreateReportService createReportService;

    @Autowired
    public SyncServiceImpl(WobTestRemakeApplication wobTestRemakeApplication, ParserService parserService, InvalidLoggerService invalidLoggerService, FtpService ftpService, DataUploaderService dataUploaderService, ValidatorService validatorService, CreateReportService createReportService) {
        this.wobTestRemakeApplication = wobTestRemakeApplication;
        this.parserService = parserService;
        this.invalidLoggerService = invalidLoggerService;
        this.ftpService = ftpService;
        this.dataUploaderService = dataUploaderService;
        this.validatorService = validatorService;
        this.createReportService = createReportService;
    }

    @Override
    public boolean syncApi(){
        if(!wobTestRemakeApplication.isCurrentlySyncing()) {
            try {
                wobTestRemakeApplication.setCurrentlySyncing(true);

                JSONArray listingList = parserService.readJsonFromUrl("https://my.api.mockaroo.com/listing?key=63304c70");
                JSONArray locationList = parserService.readJsonFromUrl("https://my.api.mockaroo.com/location?key=63304c70");
                JSONArray listingStatList = parserService.readJsonFromUrl("https://my.api.mockaroo.com/listingStatus?key=63304c70");
                JSONArray marketplaceList = parserService.readJsonFromUrl("https://my.api.mockaroo.com/marketplace?key=63304c70");

                List<LocationDTO> locationListValidated = validatorService.validateLocation(locationList);
                List<ListingStatusDTO> listingStatListValidated = validatorService.validateListingStatus(listingStatList);
                List<MarketplaceDTO> marketplaceListValidated = validatorService.validateMarketplace(marketplaceList);

                dataUploaderService.clearTables();

                dataUploaderService.uploadMarketplaces(marketplaceListValidated);
                dataUploaderService.uploadListingStats(listingStatListValidated);
                dataUploaderService.uploadLocations(locationListValidated);

                List<ListingDTO> listingListValidated = validatorService.validateListing(listingList);
                dataUploaderService.uploadListings(listingListValidated);

                invalidLoggerService.logInvalidMarketplaces(marketplaceListValidated);
                invalidLoggerService.logInvalidListingStats(listingStatListValidated);
                invalidLoggerService.logInvalidLocations(locationListValidated);
                invalidLoggerService.logInvalidListings(listingListValidated);

                createReportService.saveReport(createReportService.createReport());

                ftpService.uploadReport();

                return true;
            }catch (Exception e) {
                System.err.println("\n"+e);
                return false;
            } finally {
                wobTestRemakeApplication.setCurrentlySyncing(false);
            }
        } else {
            return false;
        }
    }

}
