package wob.test.remake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wob.test.remake.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {}
