package wob.test.remake.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "listing_status")
@EntityListeners(AuditingEntityListener.class)
public class ListingStatus {
    @Id
    private Integer id;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @OneToMany(mappedBy = "listingStatus")
    private Set<Listing> listings;

    public Set<Listing> getListings() {
        return listings;
    }

    public void setListings(Set<Listing> listings) {
        this.listings = listings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
