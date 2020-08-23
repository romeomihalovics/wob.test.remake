CREATE TABLE `location` (
  `id` varchar(36) NOT NULL,
  `manager_name` text NOT NULL,
  `phone` text NOT NULL,
  `address_primary` text NOT NULL,
  `address_secondary` text,
  `country` text NOT NULL,
  `town` text NOT NULL,
  `postal_code` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;