package wob.test.remake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wob.test.remake.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {}
