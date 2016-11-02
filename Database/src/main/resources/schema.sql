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
