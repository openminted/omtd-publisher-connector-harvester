-- CREATE database elsevier;

-- CREATE USER 'elsevier'@'%' IDENTIFIED WITH mysql_native_password BY '***';GRANT USAGE ON *.* TO 'elsevier'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
-- GRANT USAGE ON `elsevier`.* TO 'elsevier'@'%';
CREATE TABLE IF NOT EXISTS `elsevier`.`sitemaps_crawling` ( 
    `id` INT NOT NULL , 
    `url` VARCHAR(512) NOT NULL , 
    `parent_url` VARCHAR(512) NOT NULL , 
    `time_crawled` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , 
    `level` INT NOT NULL , 
    `filename` VARCHAR(256) NOT NULL 
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `elsevier`.`node_processing` (
    `id` INT(11) unsigned NOT NULL AUTO_INCREMENT,
    `url_processed` VARCHAR(512) NOT NULL,
    `time_started` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `time_finished` TIMESTAMP ,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- CREATE TABLE IF NOT EXISTS `elsevier`.`items` (
--     `parent_url_id` INT(11) NOT NULL COMMENT 'id of the url from node_processing',
--     `url_item` VARCHAR(512) NOT NULL COMMENT 'url of book chapter or journal article'
-- ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `elsevier`.`files` (
    `id` VARCHAR(512) NOT NULL COMMENT 'id of the article url from node_processing',
    `doi` VARCHAR(512) NULL COMMENT '',
    `metadata_filename` VARCHAR(512) NULL COMMENT 'filename of metadata of article',
    `pdf_filename` VARCHAR(512) NULL COMMENT 'filename of pdf of article -if any'
) ENGINE = InnoDB;