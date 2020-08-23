package wob.test.remake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wob.test.remake.entity.Report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>, ReportRepositoryCustom {}
