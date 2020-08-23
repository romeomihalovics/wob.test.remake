package wob.test.remake.dto;

import java.sql.Date;
import java.util.List;

public class ListingDTO implements ValidSetterInterface {
    private String id;
    private String title;
    private String description;
    private String inventoryId;
    private Double listingPrice;
    private String currency;
    private Integer quantity;
    private Integer listingStatus;
    private Integer marketplace;
    private Date uploadTime;
    private String ownerEmail;
    private boolean valid;
    private List<String> invalidFields;

    public ListingDTO() {
        this.valid = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Double getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Double listingPrice) {
        this.listingPrice = listingPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(Integer listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Integer marketplace) {
        this.marketplace = marketplace;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<String> invalidFields) {
        this.invalidFields = invalidFields;
    }
}
