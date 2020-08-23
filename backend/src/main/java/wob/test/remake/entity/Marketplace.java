package wob.test.remake.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "marketplace")
@EntityListeners(AuditingEntityListener.class)
public class Marketplace {
    @Id
    private Integer id;

    @Column(name = "marketplace_name", nullable = false)
    private String marketplaceName;

    @OneToMany(mappedBy = "marketplace")
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

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }

}
