package wob.test.remake.service;

import org.json.JSONArray;
import wob.test.remake.dto.ListingDTO;
import wob.test.remake.dto.ListingStatusDTO;
import wob.test.remake.dto.LocationDTO;
import wob.test.remake.dto.MarketplaceDTO;

import java.util.List;

public interface ValidatorService {
    List<ListingDTO> validateListing(JSONArray data);
    List<MarketplaceDTO> validateMarketplace(JSONArray data);
    List<ListingStatusDTO> validateListingStatus(JSONArray data);
    List<LocationDTO> validateLocation(JSONArray data);
}
