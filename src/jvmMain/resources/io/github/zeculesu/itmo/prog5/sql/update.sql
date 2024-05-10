UPDATE collection
SET name                = ?,
    coordinatesX        = ?,
    coordinatesY        = ?,
    creationDate        = ?,
    health              = ?,
    category            = ?,
    weaponType          = ?,
    meleeWeapon         = ?,
    chapterName         = ?,
    chapterParentLegion = ?
WHERE id = ?;