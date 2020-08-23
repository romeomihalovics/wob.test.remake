package wob.test.remake.repository;

import wob.test.remake.entity.Report;

public interface ReportRepositoryCustom {
    Report getReportByDate(int year, int month);
}
