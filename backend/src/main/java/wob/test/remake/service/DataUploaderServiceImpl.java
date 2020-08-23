package wob.test.remake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wob.test.remake.dto.ListingDTO;
import wob.test.remake.dto.ListingStatusDTO;
import wob.test.remake.dto.LocationDTO;
import wob.test.remake.dto.MarketplaceDTO;
import wob.test.remake.entity.Listing;
import wob.test.remake.entity.ListingStatus;
import wob.test.remake.entity.Location;
import wob.test.remake.entity.Marketplace;
import wob.test.remake.repository.ListingRepository;
import wob.test.remake.repository.ListingStatusRepository;
import wob.test.remake.repository.LocationRepository;
import wob.test.remake.repository.MarketplaceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataUploaderServiceImpl implements DataUploaderService {

    private final ListingRepository listingRepository;

    private final MarketplaceRepository marketplaceRepository;

    private final ListingStatusRepository listingStatusRepository;

    private final LocationRepository locationRepository;

    @Autowired
    public DataUploaderServiceImpl(ListingRepository listingRepository, MarketplaceRepository marketplaceRepository, ListingStatusRepository listingStatusRepository, LocationRepository locationRepository) {
        this.listingRepository = listingRepository;
        this.marketplaceRepository = marketplaceRepository;
        this.listingStatusRepository = listingStatusRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void clearTables() {
        listingRepository.deleteAll();
        marketplaceRepository.deleteAll();
        listingStatusRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Override
    public void uploadMarketplaces(List<MarketplaceDTO> marketplaceDTOList) {
        List<Marketplace> marketplaceList = new ArrayList<>();

        for(MarketplaceDTO marketplaceDTO : marketplaceDTOList){
            if(marketplaceDTO.isValid()){
                Marketplace marketplace = new Marketplace();
                marketplace.setId(marketplaceDTO.getId());
                marketplace.setMarketplaceName(marketplaceDTO.getMarketplaceName());
                marketplaceList.add(marketplace);
            }
        }

        marketplaceRepository.saveAll(marketplaceList);
    }

    @Override
    public void uploadListingStats(List<ListingStatusDTO> listingStatusDTOList) {
        List<ListingStatus> listingStatusList = new ArrayList<>();

        for(ListingStatusDTO listingStatusDTO : listingStatusDTOList) {
            if(listingStatusDTO.isValid()){
                ListingStatus listingStatus = new ListingStatus();
                listingStatus.setId(listingStatusDTO.getId());
                listingStatus.setStatusName(listingStatusDTO.getStatusName());
                listingStatusList.add(listingStatus);
            }
        }

        listingStatusRepository.saveAll(listingStatusList);
    }

    @Override
    public void uploadLocations(List<LocationDTO> locationDTOList) {
        List<Location> locationList = new ArrayList<>();

        for(LocationDTO locationDTO : locationDTOList){
            if(locationDTO.isValid()){
                Location location = new Location();
                location.setId(locationDTO.getId());
                location.setManagerName(locationDTO.getManagerName());
                location.setPhone(locationDTO.getPhone());
                location.setAddressPrimary(locationDTO.getAddressPrimary());
                location.setAddressSecondary(locationDTO.getAddressSecondary());
                location.setCountry(locationDTO.getCountry());
                location.setTown(locationDTO.getTown());
                location.setPostalCode(locationDTO.getPostalCode());
                locationList.add(location);
            }
        }

        locationRepository.saveAll(locationList);
    }

    @Override
    public void uploadListings(List<ListingDTO> listingDTOList) {
        List<Listing> listingList = new ArrayList<>();

        for(ListingDTO listingDTO : listingDTOList) {
            if(listingDTO.isValid()) {
                Listing listing = new Listing();

                Location listingLocation = locationRepository.findById(listingDTO.getInventoryId()).orElseThrow(() -> new RuntimeException("Location with id " + listingDTO.getInventoryId() + " was not found"));
                Marketplace listingMarketplace = marketplaceRepository.findById(listingDTO.getMarketplace()).orElseThrow(() -> new RuntimeException("Marketplace with id " + listingDTO.getMarketplace() + " was not found"));
                ListingStatus listingListingStatus = listingStatusRepository.findById(listingDTO.getListingStatus()).orElseThrow(() -> new RuntimeException("Listing status with id " + listingDTO.getListingStatus() + " was not found"));

                listing.setId(listingDTO.getId());
                listing.setTitle(listingDTO.getTitle());
                listing.setDescription(listingDTO.getDescription());
                listing.setInventoryId(listingLocation);
                listing.setListingPrice(listingDTO.getListingPrice());
                listing.setCurrency(listingDTO.getCurrency());
                listing.setQuantity(listingDTO.getQuantity());
                listing.setListingStatus(listingListingStatus);
                listing.setMarketplace(listingMarketplace);
                listing.setUploadTime(listingDTO.getUploadTime());
                listing.setOwnerEmail(listingDTO.getOwnerEmail());

                listingList.add(listing);
            }
        }

        listingRepository.saveAll(listingList);
    }
}
