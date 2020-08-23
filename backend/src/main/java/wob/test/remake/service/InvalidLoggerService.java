package wob.test.remake.service;

import wob.test.remake.dto.ListingDTO;
import wob.test.remake.dto.ListingStatusDTO;
import wob.test.remake.dto.LocationDTO;
import wob.test.remake.dto.MarketplaceDTO;

import java.io.IOException;
import java.util.List;

public interface InvalidLoggerService {
    void logInvalidMarketplaces(List<MarketplaceDTO> marketplaceDTOList) throws IOException;
    void logInvalidListingStats(List<ListingStatusDTO> listingStatusDTOList) throws IOException;
    void logInvalidLocations(List<LocationDTO> locationDTOList) throws IOException;
    void logInvalidListings(List<ListingDTO> listingDTOList) throws IOException;
}
