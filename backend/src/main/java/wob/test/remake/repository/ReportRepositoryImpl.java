package wob.test.remake.repository;

import wob.test.remake.entity.Report;

import javax.persistence.*;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Report getReportByDate(int year, int month) {
        Query query = em.createNativeQuery("{call report_by_date(?,?)}", Report.class)
                .setParameter(1, year)
                .setParameter(2, month);
        List<Report> reportList = query.getResultList();
        return reportList.get(0);
    }
}
