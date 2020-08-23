package wob.test.remake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wob.test.remake.entity.Listing;
import wob.test.remake.entity.ListingStatus;
import wob.test.remake.entity.Location;
import wob.test.remake.entity.Marketplace;
import wob.test.remake.repository.ListingRepository;
import wob.test.remake.repository.ListingStatusRepository;
import wob.test.remake.repository.LocationRepository;
import wob.test.remake.repository.MarketplaceRepository;
import wob.test.remake.service.CreateReportService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResultController {

    private final ListingRepository listingRepository;

    private final MarketplaceRepository marketplaceRepository;

    private final ListingStatusRepository listingStatusRepository;

    private final LocationRepository locationRepository;

    private final CreateReportService createReportService;

    @Autowired
    public ResultController(ListingRepository listingRepository, MarketplaceRepository marketplaceRepository, ListingStatusRepository listingStatusRepository, LocationRepository locationRepository, CreateReportService createReportService) {
        this.listingRepository = listingRepository;
        this.marketplaceRepository = marketplaceRepository;
        this.listingStatusRepository = listingStatusRepository;
        this.locationRepository = locationRepository;
        this.createReportService = createReportService;
    }

    @GetMapping("/listings")
    public List<Listing> getAllListings() { return listingRepository.findAll(); }

    @GetMapping("/marketplaces")
    public List<Marketplace> getAllMarketplace() { return marketplaceRepository.findAll(); }

    @GetMapping("/locations")
    public List<Location> getAllLocation() { return locationRepository.findAll(); }

    @GetMapping("/listingstats")
    public List<ListingStatus> getAllListingStatus() { return listingStatusRepository.findAll(); }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReport() throws IOException, ParseException {
        return new ResponseEntity<>(createReportService.createReport().toMap(), HttpStatus.OK);
    }
}
