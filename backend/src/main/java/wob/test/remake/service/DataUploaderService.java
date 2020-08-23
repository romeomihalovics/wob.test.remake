package wob.test.remake.service;

import wob.test.remake.dto.ListingDTO;
import wob.test.remake.dto.ListingStatusDTO;
import wob.test.remake.dto.LocationDTO;
import wob.test.remake.dto.MarketplaceDTO;

import java.util.List;

public interface DataUploaderService {
    void uploadMarketplaces(List<MarketplaceDTO> marketplaceDTOList);
    void uploadListingStats(List<ListingStatusDTO> listingStatusDTOList);
    void uploadLocations(List<LocationDTO> locationDTOList);
    void uploadListings(List<ListingDTO> listingDTOList);
    void clearTables();
}
