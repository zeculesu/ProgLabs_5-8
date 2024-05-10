CREATE type ASTARTESCATEGORY as ENUM ('SCOUT', 'SUPPRESSOR', 'LIBRARIAN', 'HELIX');
CREATE TYPE MELEEWEAPON as ENUM ('CHAIN_SWORD', 'POWER_SWORD', 'CHAIN_AXE', 'MANREAPER', 'POWER_BLADE');
CREATE TYPE WEAPONTYPE as ENUM ('BOLTGUN', 'HEAVY_BOLTGUN', 'BOLT_RIFLE', 'FLAMER', 'MULTI_MELTA');

CREATE TABLE collection
(
    id                  SERIAL PRIMARY KEY,
    name                TEXT,
    coordinatesX        BIGINT,
    coordinatesY        FLOAT,
    creationDate        DATE,
    health              INT,
    category            ASTARTESCATEGORY,
    weaponType          WEAPONTYPE,
    meleeWeapon         MELEEWEAPON,
    chapterName         TEXT,
    chapterParentLegion TEXT,
    owner               TEXT references users (login)
);

CREATE TABLE users
(
    login    TEXT PRIMARY KEY,
    password TEXT NOT NULL
);