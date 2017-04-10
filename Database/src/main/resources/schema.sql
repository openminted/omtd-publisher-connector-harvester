-- CREATE database elsevier;

-- CREATE USER 'elsevier'@'%' IDENTIFIED WITH mysql_native_password BY '***';GRANT USAGE ON *.* TO 'elsevier'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
-- GRANT USAGE ON `elsevier`.* TO 'elsevier'@'%';
-- CREATE TABLE IF NOT EXISTS `elsevier`.`sitemaps_crawling` ( 
--     `id` INT NOT NULL , 
--     `url` VARCHAR(512) NOT NULL , 
--     `parent_url` VARCHAR(512) NOT NULL , 
--     `time_crawled` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , 
--     `level` INT NOT NULL , 
--     `filename` VARCHAR(256) NOT NULL 
-- ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `elsevier`.`sitemaps_crawling` (
 `id` int(11) NOT NULL,
 `url` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
 `url_hex_hash` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
 `parent_url` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
 `time_crawled` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `level` int(11) NOT NULL,
 `filename` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
 PRIMARY KEY (`url_hex_hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `elsevier`.`node_processing` (
    `id` INT(11) unsigned NOT NULL AUTO_INCREMENT,
    `url_processed` VARCHAR(512) NOT NULL,
    `time_started` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `time_finished` TIMESTAMP ,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- CREATE TABLE IF NOT EXISTS `elsevier`.`items` (
--     `parent_url_id` INT(11) NOT NULL COMMENT 'id of the url from node_processing',
--     `url_item` VARCHAR(512) NOT NULL COMMENT 'url of book chapter or journal article'
-- ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `elsevier`.`files` (
    `id` VARCHAR(512) NOT NULL COMMENT 'id of the article url from node_processing',
    `doi` VARCHAR(512) NULL COMMENT '',
    `metadata_filename` VARCHAR(512) NULL COMMENT 'filename of metadata of article',
    `pdf_filename` VARCHAR(512) NULL COMMENT 'filename of pdf of article -if any'
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `elsevier`.`generic_files` (
    `id` INT(11) unsigned NOT NULL AUTO_INCREMENT,
    `publisher_prefix` VARCHAR(128) NOT NULL,
    `doi` VARCHAR(256) NOT NULL,
    `metadata` TEXT NULL,
    `metadata_filename` VARCHAR(512) NULL COMMENT 'filename of metadata of article',
    `pdf_filename` VARCHAR(512) NULL COMMENT 'filename of pdf of article -if any',
    `time_accessed` timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `elsevier`.`publisher_doi_discovery_log` (
    `id` INT(11) unsigned NOT NULL AUTO_INCREMENT,
    `publisher` VARCHAR(128) NOT NULL,
    `day` VARCHAR(16) NOT NULL,
    `time_commenced` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `time_finished` timestamp NULL,
    `retrieved_count` INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

