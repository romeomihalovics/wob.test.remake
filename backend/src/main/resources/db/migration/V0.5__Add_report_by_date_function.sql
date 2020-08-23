CREATE PROCEDURE
    report_by_date(reportYear INT, reportMonth INT)
BEGIN
    SELECT
        count(id) AS 'total_listings',
        (
            SELECT
                upload_time
            FROM listing
            WHERE
                year(upload_time) = reportYear
              AND
                month(upload_time) = reportMonth
            ORDER BY upload_time ASC
            LIMIT 1
        ) AS 'oldest_listing',
        (
            SELECT
                upload_time
            FROM listing
            WHERE
                year(upload_time) = reportYear
              AND
                month(upload_time) = reportMonth
            ORDER BY upload_time DESC
            LIMIT 1
        ) AS 'newest_listing',
        (
            SELECT
                count(m.id)
            FROM `listing` AS l
                     INNER JOIN `marketplace` AS m
                                ON l.marketplace = m.id
            WHERE
                    m.marketplace_name = 'Ebay'
              AND
                    year(upload_time) = reportYear
              AND
                    month(upload_time) = reportMonth
        ) AS 'total_ebay_listings',
        (
            SELECT
                ROUND(sum(l.listing_price), 2)
            FROM `listing` AS l
                     INNER JOIN `marketplace` AS m
                                ON l.marketplace = m.id
            WHERE
                    m.marketplace_name = 'Ebay'
              AND
                    year(upload_time) = reportYear
              AND
                    month(upload_time) = reportMonth
        ) AS 'total_ebay_listing_price',
        (
            SELECT
                ROUND(avg(l.listing_price), 2)
            FROM `listing` AS l
                     INNER JOIN `marketplace` AS m
                                ON l.marketplace = m.id
            WHERE
                    m.marketplace_name = 'Ebay'
              AND
                    year(upload_time) = reportYear
              AND
                    month(upload_time) = reportMonth
        ) AS 'avg_ebay_listing_price',
        (
            SELECT
                count(m.id)
            FROM `listing` AS l
                     INNER JOIN `marketplace` AS m
                                ON l.marketplace = m.id
            WHERE
                    m.marketplace_name = 'Amazon'
              AND
                    year(upload_time) = reportYear
              AND
                    month(upload_time) = reportMonth
        ) AS 'total_amazon_listings',
        (
            SELECT
                ROUND(sum(l.listing_price), 2)
            FROM `listing` AS l
                     INNER JOIN `marketplace` AS m
                                ON l.marketplace = m.id
            WHERE
                m.marketplace_name = 'Amazon'
              AND
                year(upload_time) = reportYear
              AND
                month(upload_time) = reportMonth
        ) AS 'total_amazon_listing_price',
        (
            SELECT
                ROUND(avg(l.listing_price), 2)
            FROM `listing` AS l
                     INNER JOIN `marketplace` AS m
                                ON l.marketplace = m.id
            WHERE
                m.marketplace_name = 'Amazon'
              AND
                year(upload_time) = reportYear
              AND
                month(upload_time) = reportMonth
        ) AS 'avg_amazon_listing_price',
        (
            SELECT
                `owner_email`
            FROM `listing`
            WHERE
                    year(upload_time) = reportYear
              AND
                    month(upload_time) = reportMonth
            GROUP BY `owner_email`
            ORDER BY count(`owner_email`) DESC
            LIMIT 1
        ) AS 'best_lister',
        (
            SELECT
                count(`owner_email`) as listings
            FROM `listing`
            WHERE
                year(upload_time) = reportYear
              AND
                month(upload_time) = reportMonth
            GROUP BY `owner_email`
            ORDER BY listings DESC
            LIMIT 1
        ) AS 'best_lister_listings'
    FROM `listing`
    WHERE
          year(upload_time) = reportYear
      AND
          month(upload_time) = reportMonth;
END;