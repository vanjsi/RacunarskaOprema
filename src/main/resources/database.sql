CREATE SCHEMA IF NOT EXISTS `racunarska_oprema` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `racunarska_oprema`;

-- -----------------------------------------------------
-- Table `racunarska_oprema`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarska_oprema`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL UNIQUE,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `birth_date` DATE NULL,
  `account_balance` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_user`));

-- -----------------------------------------------------
-- Table `racunarska_oprema`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarska_oprema`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `stock` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_product`));

-- -----------------------------------------------------
-- Table `racunarska_oprema`.`search_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarska_oprema`.`search_settings` (
  `id_search_settings` INT NOT NULL AUTO_INCREMENT,
  `price_min` DECIMAL(10,2) NULL,
  `price_max` DECIMAL(10,2) NULL,
  `product_type` VARCHAR(50) NULL,
  PRIMARY KEY (`id_search_settings`));

-- -----------------------------------------------------
-- Table `racunarska_oprema`.`search`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarska_oprema`.`search` (
  `id_search` INT NOT NULL AUTO_INCREMENT,
  `fk_user` INT NOT NULL,
  `fk_search_settings` INT NOT NULL,
  `keyword` VARCHAR(100) NOT NULL,
  `search_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_search`),
  INDEX `fk_search_user_idx` (`fk_user` ASC),
  INDEX `fk_search_settings_idx` (`fk_search_settings` ASC),
  CONSTRAINT `fk_search_user`
    FOREIGN KEY (`fk_user`)
    REFERENCES `racunarska_oprema`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_search_settings`
    FOREIGN KEY (`fk_search_settings`)
    REFERENCES `racunarska_oprema`.`search_settings` (`id_search_settings`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `racunarska_oprema`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarska_oprema`.`purchase` (
  `id_purchase` INT NOT NULL AUTO_INCREMENT,
  `fk_user` INT NOT NULL,
  `fk_product` INT NOT NULL,
  `purchase_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_purchase`),
  INDEX `fk_purchase_user_idx` (`fk_user` ASC),
  INDEX `fk_purchase_product_idx` (`fk_product` ASC),
  CONSTRAINT `fk_purchase_user`
    FOREIGN KEY (`fk_user`)
    REFERENCES `racunarska_oprema`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_purchase_product`
    FOREIGN KEY (`fk_product`)
    REFERENCES `racunarska_oprema`.`product` (`id_product`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);