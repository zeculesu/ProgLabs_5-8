package io.github.zeculesu.itmo.prog5.models;

import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.net.EnumIdMapper;
import io.github.zeculesu.itmo.prog5.net.EnumSerializer;
import io.github.zeculesu.itmo.prog5.net.NetObject;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import static kotlin.collections.ArraysKt.associateBy;

/**
 * Возможные типы оружия для SpaceMarine
 */
public enum Weapon implements NetObject<Weapon> {
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

    public static Weapon getWeaponByName(String weaponName) throws NamingEnumException {
        if (name2instance.get(weaponName) == null) {
            throw new NamingEnumException("Неправильное имя для оружия");
        }
        return name2instance.get(weaponName);
    }

    public static final NetObjectSerializer<Weapon> Serializer = new EnumSerializer<>(EnumIdMapper
            .builder(Weapon.class)
            .bind(0, BOLTGUN)
            .bind(1, HEAVY_BOLTGUN)
            .bind(2, BOLT_RIFLE)
            .bind(3, FLAMER)
            .bind(4, MULTI_MELTA)
            .build()
    );

    @Override
    public NetObjectSerializer<Weapon> getSerializer() {
        return Weapon.Serializer;
    }
}