package wob.test.remake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wob.test.remake.entity.ListingStatus;

@Repository
public interface ListingStatusRepository extends JpaRepository<ListingStatus, Integer> {}
