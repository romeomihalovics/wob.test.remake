package wob.test.remake.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "listing")
@EntityListeners(AuditingEntityListener.class)
public class Listing {
    @Id
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnoreProperties(value = {"listings"})
    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Location inventoryId;

    @Column(name = "listing_price", nullable = false)
    private Double listingPrice;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @JsonIgnoreProperties(value = {"listings"})
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "listing_status", nullable = false)
    private ListingStatus listingStatus;

    @JsonIgnoreProperties(value = {"listings"})
    @ManyToOne
    @JoinColumn(name = "marketplace", nullable = false)
    private Marketplace marketplace;

    @Column(name = "upload_time", nullable = false)
    private Date uploadTime;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

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

    public Location getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Location inventoryId) {
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

    public ListingStatus getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(ListingStatus listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
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
}
