package wob.test.remake.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Immutable
@Table(name = "report_view")
public class Report {
    @Id
    @Column(name = "total_listings")
    private Integer totalListings;

    @Column(name = "oldest_listing")
    private Date oldestListing;

    @Column(name = "newest_listing")
    private Date newestListing;

    @Column(name = "total_ebay_listings")
    private Integer totalEbayListings;

    @Column(name = "total_ebay_listing_price")
    private Double totalEbayListingPrice;

    @Column(name = "avg_ebay_listing_price")
    private Double avgEbayListingPrice;

    @Column(name = "total_amazon_listings")
    private Integer totalAmazonListings;

    @Column(name = "total_amazon_listing_price")
    private Double totalAmazonListingPrice;

    @Column(name = "avg_amazon_listing_price")
    private Double avgAmazonListingPrice;

    @Column(name = "best_lister")
    private String bestLister;

    @Column(name = "best_lister_listings")
    private String bestListerListings;

    public Integer getTotalListings() {
        return totalListings;
    }

    public Date getOldestListing() {
        return oldestListing;
    }

    public Date getNewestListing() {
        return newestListing;
    }

    public Integer getTotalEbayListings() {
        return totalEbayListings;
    }

    public Double getTotalEbayListingPrice() {
        return totalEbayListingPrice;
    }

    public Double getAvgEbayListingPrice() {
        return avgEbayListingPrice;
    }

    public Integer getTotalAmazonListings() {
        return totalAmazonListings;
    }

    public Double getTotalAmazonListingPrice() {
        return totalAmazonListingPrice;
    }

    public Double getAvgAmazonListingPrice() {
        return avgAmazonListingPrice;
    }

    public String getBestLister() {
        return bestLister;
    }

    public String getBestListerListings() {
        return bestListerListings;
    }
}
