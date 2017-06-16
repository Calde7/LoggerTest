CREATE TABLE `LOGGER` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(64) DEFAULT NULL,
  `message` varchar(64) DEFAULT NULL,
  `date_of` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1
