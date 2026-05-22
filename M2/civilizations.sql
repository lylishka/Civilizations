CREATE DATABASE IF NOT EXISTS civilizations;
USE civilizations;

/*
============================
 TABLA civilizacion_stats
============================
*/

CREATE TABLE civilizacion_stats (
	civilization_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    wood_amount INT DEFAULT 0,
    iron_amount INT DEFAULT 0,
    food_amount INT DEFAULT 0,
    mana_amount INT DEFAULT 0,
    magicTower_counter INT DEFAULT 0,
    church_counter INT DEFAULT 0,
    farm_counter INT DEFAULT 0,
    smithy_counter INT DEFAULT 0,
    carpentry_counter INT DEFAULT 0,
    technology_defense_level INT DEFAULT 0,
    technology_attack_level INT DEFAULT 0,
    battles_counter INT DEFAULT 0
);



/*
==========================
 TABLA attack_units_stats
==========================
*/

CREATE TABLE attack_units_stats (
	unit_id INT AUTO_INCREMENT PRIMARY KEY,
    civilization_id INT NOT NULL,
    type ENUM('Swordsman','Spearman','Crossbow', 'Cannon'),
    armor INT,
    base_damage INT,
    experience INT DEFAULT 0,
    sanctified TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id)
);



/*
===========================
 TABLA defense_units_stats
===========================
*/

CREATE TABLE defense_units_stats (
	unit_id INT AUTO_INCREMENT PRIMARY KEY,
    civilization_id INT NOT NULL,
    type ENUM('ArrowTower','Catapult','RocketLauncherTower'),
    armor INT,
    base_damage INT,
    experience INT,
    sanctified TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id)
);



/*
============================
 TABLA special_units_stats
============================
*/

CREATE TABLE special_units_stats (
	unit_id INT AUTO_INCREMENT PRIMARY KEY,
    civilization_id INT NOT NULL,
    type ENUM('Magician','Priest'),
    armor INT DEFAULT 0,
    base_damage INT,
    experience INT,
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id)
);



/*
===========================
 TABLA battle_stats
===========================
*/

CREATE TABLE battle_stats (
	num_battle INT AUTO_INCREMENT PRIMARY KEY,
    civilization_id INT NOT NULL,
    wood_acquired INT DEFAULT 0,
    iron_acquired INT DEFAULT 0,
    battle_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id)
);



/*
===========================
 TABLA battle_log
===========================
*/

CREATE TABLE battle_log (
	num_line INT AUTO_INCREMENT PRIMARY KEY,
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    winner VARCHAR(100) NOT NULL,
    log_entry TEXT,
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id),
    FOREIGN KEY (num_battle) REFERENCES battle_stats(num_battle)
);



/*
=================================
 TABLA civilization_attack_stats
=================================
*/

CREATE TABLE civilization_attack_stats (
	type ENUM('Swordsman','Spearman', 'Crossbow', 'Cannon'),
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    posX INT DEFAULT 0,
    posY INT DEFAULT 0,
    PRIMARY KEY (type, civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id),
    FOREIGN KEY (num_battle) REFERENCES battle_stats(num_battle)
);



/*
==================================
 TABLA civilization_defense_stats
==================================
*/

CREATE TABLE civilization_defense_stats (
	type ENUM('ArrowTower','Catapult','RocketLauncherTower'),
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    posX INT DEFAULT 0,
    posY INT DEFAULT 0,
    PRIMARY KEY (type, civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id),
    FOREIGN KEY (num_battle) REFERENCES battle_stats(num_battle)
);



/*
==================================
 TABLA civilization_special_stats
==================================
*/

CREATE TABLE civilization_special_stats (
	type ENUM('Magician','Priest'),
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    initial INT DEFAULT 0,
    drops INT DEFAULT 0,
    posX INT DEFAULT 0,
    posY INT DEFAULT 0,
    PRIMARY KEY (type, civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id),
    FOREIGN KEY (num_battle) REFERENCES battle_stats(num_battle)
);



/*
=================================
 TABLA enemy_attack_stats
=================================
*/

CREATE TABLE enemy_attack_stats (
	type ENUM('Swordsman','Spearman', 'Crossbow', 'Cannon'),
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    initial INT,
    drops INT,
    PRIMARY KEY (type, civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id),
    FOREIGN KEY (num_battle) REFERENCES battle_stats(num_battle)
);



/*
=================================
 TABLA building
=================================
*/

CREATE TABLE building (
	bulding_id INT AUTO_INCREMENT PRIMARY KEY,
    civilization_id INT NOT NULL,
	type ENUM('MagicTower','Church', 'Farm', 'Smithy', 'Carpentry'),
    posX INT DEFAULT 0,
    posY INT DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilizacion_stats(civilization_id)
);