package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.error.NamingEnumException;

import java.util.Map;

import static kotlin.collections.ArraysKt.associateBy;

public enum Weapon {
    BOLTGUN("BOLTGUN"),

    HEAVY_BOLTGUN("HEAVY_BOLTGUN"),

    BOLT_RIFLE("BOLT_RIFLE"),

    FLAMER("FLAMER"),

    MULTI_MELTA("MULTI_MELTA");

    private final String weaponName;
    private static final Map<String, Weapon> name2instance = associateBy(Weapon.values(), v -> v.weaponName);

    Weapon(String weaponName) {
        this.weaponName = weaponName;
    }

    public static Weapon getWeaponByName(String weaponName) throws NamingEnumException{
        if (name2instance.get(weaponName) == null){
            throw new NamingEnumException("Неправильное имя для оружия");
        }
        return name2instance.get(weaponName);

    }
}