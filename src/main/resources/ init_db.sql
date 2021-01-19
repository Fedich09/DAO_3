CREATE SCHEMA `taxi_service` DEFAULT CHARACTER SET utf8;
CREATE TABLE `taxi_service`.`manufacturers`
(
    `manufacturer_id`      BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `manufacturer_name`    VARCHAR(225) NULL,
    `manufacturer_country` VARCHAR(225) NULL,
    `is_delete`            TINYINT '0',
    PRIMARY KEY (`manufacturer_id`)
);
CREATE TABLE `taxi_service`.`cars`
(
    `car_id`          BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `model`           VARCHAR(225) NOT NULL,
    `manufacturer_id` BIGINT(11)   NOT NULL,
    `is_delete`       TINYINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`car_id`),
    INDEX `cars_manufacturers_fk_idx` (`manufacturer_id` ASC) VISIBLE,
    CONSTRAINT `cars_manufacturers_fk`
        FOREIGN KEY (`manufacturer_id`)
            REFERENCES `taxi_service`.`manufacturers` (`manufacturer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `taxi_service`.`cars_drivers`
(
    `driver_id` BIGINT(11) NOT NULL,
    `car_id`    BIGINT(11) NOT NULL
);

CREATE TABLE `taxi_service`.`drivers`
(
    `driver_id`      BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`           VARCHAR(255) NOT NULL,
    `license_number` VARCHAR(225) NOT NULL,
    `is_delete`      TINYINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`driver_id`)
);

ALTER TABLE `taxi_service`.`cars_drivers`
    ADD INDEX `cars_fk_idx` (`car_id` ASC) VISIBLE;
;
ALTER TABLE `taxi_service`.`cars_drivers`
    ADD CONSTRAINT `cars_fk`
        FOREIGN KEY (`car_id`)
            REFERENCES `taxi_service`.`cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `taxi_service`.`cars_drivers`
    ADD INDEX `drivers_fk_idx` (`driver_id` ASC) VISIBLE;
;
ALTER TABLE `taxi_service`.`cars_drivers`
    ADD CONSTRAINT `drivers_fk`
        FOREIGN KEY (`driver_id`)
            REFERENCES `taxi_service`.`drivers` (`driver_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


