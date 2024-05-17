INSERT INTO collection (name,
                        coordinatesX,
                        coordinatesY,
                        creationDate,
                        health,
                        category,
                        weaponType,
                        meleeWeapon,
                        chapterName,
                        chapterParentLegion)
VALUES (?, ?, ?, ?, ?, CAST(? AS ASTARTESCATEGORY), CAST(? AS WEAPONTYPE), CAST(? AS MELEEWEAPON), ?, ?);

-- todo add owner