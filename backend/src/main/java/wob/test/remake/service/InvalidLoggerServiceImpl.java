package wob.test.remake.service;

import org.springframework.stereotype.Service;
import wob.test.remake.dto.ListingDTO;
import wob.test.remake.dto.ListingStatusDTO;
import wob.test.remake.dto.LocationDTO;
import wob.test.remake.dto.MarketplaceDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class InvalidLoggerServiceImpl implements InvalidLoggerService {

    @Override
    public void logInvalidMarketplaces(List<MarketplaceDTO> marketplaceDTOList) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("importLog_Marketplaces.csv", false)));
        writer.println("id;marketplace_name;invalid_fields");

        for (MarketplaceDTO marketplaceDTO : marketplaceDTOList) {
            if (!marketplaceDTO.isValid()) {
                writer.println(marketplaceDTO.getId() + ";"
                        + marketplaceDTO.getMarketplaceName() + ";"
                        + String.join(",", marketplaceDTO.getInvalidFields()));
            }
        }

        writer.close();
    }

    @Override
    public void logInvalidListingStats(List<ListingStatusDTO> listingStatusDTOList) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("importLog_ListingStats.csv", false)));
        writer.println("id;status_name;invalid_fields");

        for (ListingStatusDTO listingStatusDTO : listingStatusDTOList) {
            if (!listingStatusDTO.isValid()) {
                writer.println(listingStatusDTO.getId() + ";"
                        + listingStatusDTO.getStatusName() + ";"
                        + String.join(",", listingStatusDTO.getInvalidFields()));
            }
        }

        writer.close();
    }

    @Override
    public void logInvalidLocations(List<LocationDTO> locationDTOList) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("importLog_Locations.csv", false)));
        writer.println("id;manager_name;phone;address_primary;address_secondary;country;town;postal_code;invalid_fields");

        for (LocationDTO locationDTO : locationDTOList) {
            if (!locationDTO.isValid()) {
                writer.println(locationDTO.getId() + ";"
                        + locationDTO.getPhone() + ";"
                        + locationDTO.getAddressPrimary() + ";"
                        + locationDTO.getAddressSecondary() + ";"
                        + locationDTO.getCountry() + ";"
                        + locationDTO.getTown() + ";"
                        + locationDTO.getPostalCode() + ";"
                        + String.join(",", locationDTO.getInvalidFields()));
            }
        }

        writer.close();
    }

    @Override
    public void logInvalidListings(List<ListingDTO> listingDTOList) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("importLog_Listings.csv", false)));
        writer.println("id;title;description;location_id;listing_price;currency;quantity;listing_status;marketplace;upload_time;owner_email_address;invalid_fields");

        for (ListingDTO listingDTO : listingDTOList) {
            if (!listingDTO.isValid()) {
                writer.println(listingDTO.getId() + ";"
                        + listingDTO.getTitle() + ";"
                        + listingDTO.getDescription() + ";"
                        + listingDTO.getInventoryId() + ";"
                        + listingDTO.getListingPrice() + ";"
                        + listingDTO.getCurrency() + ";"
                        + listingDTO.getQuantity() + ";"
                        + listingDTO.getListingStatus() + ";"
                        + listingDTO.getMarketplace() + ";"
                        + listingDTO.getUploadTime() + ";"
                        + listingDTO.getOwnerEmail() + ";"
                        + String.join(",", listingDTO.getInvalidFields()));
            }
        }

        writer.close();
    }
}
