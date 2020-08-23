package wob.test.remake.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wob.test.remake.dto.*;
import wob.test.remake.repository.ListingStatusRepository;
import wob.test.remake.repository.LocationRepository;
import wob.test.remake.repository.MarketplaceRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    private final MarketplaceRepository marketplaceRepository;

    private final ListingStatusRepository listingStatusRepository;

    private final LocationRepository locationRepository;

    @Autowired
    public ValidatorServiceImpl(MarketplaceRepository marketplaceRepository, ListingStatusRepository listingStatusRepository, LocationRepository locationRepository){
        this.marketplaceRepository = marketplaceRepository;
        this.listingStatusRepository = listingStatusRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<ListingDTO> validateListing(JSONArray data) {
        List<ListingDTO> result = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            List<String> invalidFields = new ArrayList<>();
            ListingDTO tmpListing = new ListingDTO();

            tmpListing.setId(data.getJSONObject(i).get("id").toString());
            nullOrEmpty(tmpListing.getId(), "id", tmpListing, invalidFields);
            isUUID(tmpListing.getId(), "id", tmpListing, invalidFields);

            tmpListing.setTitle(data.getJSONObject(i).get("title").toString());
            nullOrEmpty(tmpListing.getTitle(), "title", tmpListing, invalidFields);

            tmpListing.setDescription(data.getJSONObject(i).get("description").toString());
            nullOrEmpty(tmpListing.getDescription(), "description", tmpListing, invalidFields);

            tmpListing.setInventoryId(data.getJSONObject(i).get("location_id").toString());
            nullOrEmpty(tmpListing.getInventoryId(), "location_id", tmpListing, invalidFields);
            isUUID(tmpListing.getInventoryId(), "location_id", tmpListing, invalidFields);
            if(!locationRepository.existsById(tmpListing.getInventoryId())){
                tmpListing.setValid(false);
                invalidFields.add("location_id (not found in database)");
            }

            validateDoublePrice(data.getJSONObject(i).get("listing_price").toString(), tmpListing, invalidFields);
            isPrice(tmpListing.getListingPrice(), tmpListing, invalidFields);

            tmpListing.setCurrency(data.getJSONObject(i).get("currency").toString());
            nullOrEmpty(tmpListing.getCurrency(), "currency", tmpListing, invalidFields);
            isCurrency(tmpListing.getCurrency(), tmpListing, invalidFields);

            validateQuantity(data.getJSONObject(i).get("quantity").toString(), tmpListing, invalidFields);
            isQuantity(tmpListing.getQuantity(), tmpListing, invalidFields);

            validateListingStatus(data.getJSONObject(i).get("listing_status").toString(), tmpListing, invalidFields);
            if(!listingStatusRepository.existsById(tmpListing.getListingStatus())){
                tmpListing.setValid(false);
                invalidFields.add("listing_status (not found in database)");
            }

            validateMarketplace(data.getJSONObject(i).get("marketplace").toString(), tmpListing, invalidFields);
            if(!marketplaceRepository.existsById(tmpListing.getMarketplace())){
                tmpListing.setValid(false);
                invalidFields.add("marketplace (not found in database)");
            }

            validateUploadDate(data.getJSONObject(i).get("upload_time").toString(), tmpListing, invalidFields);

            tmpListing.setOwnerEmail(data.getJSONObject(i).get("owner_email_address").toString());
            isEmail(tmpListing.getOwnerEmail(), tmpListing, invalidFields);

            tmpListing.setInvalidFields(invalidFields);
            result.add(tmpListing);
        }

        return result;
    }

    @Override
    public List<MarketplaceDTO> validateMarketplace(JSONArray data) {
        List<MarketplaceDTO> result = new ArrayList<>();

        for(int i = 0; i < data.length(); i++){
            List<String> invalidFields = new ArrayList<>();
            MarketplaceDTO tmpMarketplace = new MarketplaceDTO();

            validateIntId(data.getJSONObject(i).get("id").toString(),  tmpMarketplace, tmpMarketplace, invalidFields);

            tmpMarketplace.setMarketplaceName(data.getJSONObject(i).get("marketplace_name").toString());
            nullOrEmpty(tmpMarketplace.getMarketplaceName(), "marketplace_name", tmpMarketplace, invalidFields);

            tmpMarketplace.setInvalidFields(invalidFields);
            result.add(tmpMarketplace);
        }

        return result;
    }

    @Override
    public List<ListingStatusDTO> validateListingStatus(JSONArray data) {
        List<ListingStatusDTO> result = new ArrayList<>();

        for(int i = 0; i < data.length(); i++) {
            List<String> invalidFields = new ArrayList<>();
            ListingStatusDTO tmpListingStatus = new ListingStatusDTO();

            validateIntId(data.getJSONObject(i).get("id").toString(), tmpListingStatus, tmpListingStatus, invalidFields);

            tmpListingStatus.setStatusName(data.getJSONObject(i).get("status_name").toString());
            nullOrEmpty(tmpListingStatus.getStatusName(), "status_name", tmpListingStatus, invalidFields);

            tmpListingStatus.setInvalidFields(invalidFields);
            result.add(tmpListingStatus);
        }

        return result;
    }

    @Override
    public List<LocationDTO> validateLocation(JSONArray data) {
        List<LocationDTO> result = new ArrayList<>();

        for(int i = 0; i < data.length(); i++) {
            List<String> invalidFields = new ArrayList<>();
            LocationDTO tmpLocation = new LocationDTO();

            tmpLocation.setId(data.getJSONObject(i).get("id").toString());
            nullOrEmpty(tmpLocation.getId(), "id", tmpLocation, invalidFields);
            isUUID(tmpLocation.getId(), "id", tmpLocation, invalidFields);

            tmpLocation.setManagerName(data.getJSONObject(i).get("manager_name").toString());
            nullOrEmpty(tmpLocation.getManagerName(), "manager_name", tmpLocation, invalidFields);

            tmpLocation.setPhone(data.getJSONObject(i).get("phone").toString());
            nullOrEmpty(tmpLocation.getPhone(), "phone", tmpLocation, invalidFields);

            tmpLocation.setAddressPrimary(data.getJSONObject(i).get("address_primary").toString());
            nullOrEmpty(tmpLocation.getAddressPrimary(), "address_primary", tmpLocation, invalidFields);

            tmpLocation.setAddressSecondary(data.getJSONObject(i).get("address_secondary").toString());

            tmpLocation.setCountry(data.getJSONObject(i).get("country").toString());
            nullOrEmpty(tmpLocation.getCountry(), "country", tmpLocation, invalidFields);

            tmpLocation.setTown(data.getJSONObject(i).get("town").toString());
            nullOrEmpty(tmpLocation.getTown(), "town", tmpLocation, invalidFields);

            tmpLocation.setPostalCode(data.getJSONObject(i).get("postal_code").toString());
            nullOrEmpty(tmpLocation.getPostalCode(), "postal_code", tmpLocation, invalidFields);

            tmpLocation.setInvalidFields(invalidFields);
            result.add(tmpLocation);
        }
        return result;
    }

    private void nullOrEmpty(String string, String field, ValidSetterInterface object, List<String> invalidFields) {
        if(string.equals("null") || string.isEmpty()){
            object.setValid(false);
            invalidFields.add(field);
        }
    }

    private void isUUID(String string, String field, ValidSetterInterface object, List<String> invalidFields) {
        if(!string.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")){
            object.setValid(false);
            invalidFields.add(field + " (needs to be uuid format)");
        }
    }

    private void isQuantity(Integer number, ValidSetterInterface object, List<String> invalidFields) {
        if(number <= 0) {
            object.setValid(false);
            invalidFields.add("quantity (has to be greater than 0)");
        }
    }

    private void isPrice(Double number, ValidSetterInterface object, List<String> invalidFields){
        String afterDot = number.toString().split("\\.")[1];
        if(number <= 0 || afterDot.length() != 2){
            object.setValid(false);
            invalidFields.add("listing_price (has to be greater than 0 with 2 decimal places)");
        }
    }

    private void isCurrency(String string, ValidSetterInterface object, List<String> invalidFields) {
        if(string.length() != 3) {
            object.setValid(false);
            invalidFields.add("currency (has to be 3 letters)");
        }
    }

    @SuppressWarnings("all")
    private void isEmail(String string, ValidSetterInterface object, List<String> invalidFields) {
        Pattern pattern = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
        if(!pattern.matcher(string).matches()){
            object.setValid(false);
            invalidFields.add("owner_email_address (has to be a valid email address)");
        }
    }

    private void validateIntId(String string, IdSetterInterface objectId, ValidSetterInterface objectValid, List<String> invalidFields) {
        try {
            Integer tmpId = Integer.parseInt(string);
            objectId.setId(tmpId);
        } catch (NumberFormatException e) {
            objectValid.setValid(false);
            invalidFields.add("id (with value: '" + string + "')");
            objectId.setId(-1);
        }
    }

    private void validateDoublePrice(String string, ListingDTO object, List<String> invalidFields) {
        try {
            Double tmpDouble = Double.parseDouble(string);
            object.setListingPrice(tmpDouble);
        } catch (NumberFormatException e) {
            object.setValid(false);
            invalidFields.add("listing_price (with value: '" + string + "')");
            object.setListingPrice(-1.00);
        }
    }

    private void validateQuantity(String string, ListingDTO object, List<String> invalidFields) {
        try {
            Integer tmpInt = Integer.parseInt(string);
            object.setQuantity(tmpInt);
        } catch (NumberFormatException e) {
            object.setValid(false);
            invalidFields.add("quantity (with value: '" + string + "')");
            object.setQuantity(-1);
        }
    }

    private void validateMarketplace(String string, ListingDTO object, List<String> invalidFields) {
        try {
            Integer tmpInt = Integer.parseInt(string);
            object.setMarketplace(tmpInt);
        } catch (NumberFormatException e) {
            object.setValid(false);
            invalidFields.add("marketplace (with value: '" + string + "')");
            object.setMarketplace(-1);
        }
    }

    private void validateListingStatus(String string, ListingDTO object, List<String> invalidFields) {
        try {
            Integer tmpInt = Integer.parseInt(string);
            object.setListingStatus(tmpInt);
        } catch (NumberFormatException e) {
            object.setValid(false);
            invalidFields.add("listing_status (with value: '" + string + "')");
            object.setListingStatus(-1);
        }
    }

    private void validateUploadDate(String string, ListingDTO object, List<String> invalidFields) {
        DateFormat tempFormat = new SimpleDateFormat("M/d/yyyy");
        try {
            Date tempDate = tempFormat.parse(string);
            object.setUploadTime(new java.sql.Date(tempDate.getTime()));
        } catch (ParseException e) {
            Date now = new Date();
            object.setUploadTime(new java.sql.Date(now.getTime()));
            object.setValid(false);
            invalidFields.add("upload_time (with value: '" + string + "')");
        }
    }

}
