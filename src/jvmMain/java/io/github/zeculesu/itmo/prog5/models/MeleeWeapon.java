package io.github.zeculesu.itmo.prog5.models;

import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.net.EnumIdMapper;
import io.github.zeculesu.itmo.prog5.net.EnumSerializer;
import io.github.zeculesu.itmo.prog5.net.NetObject;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;
import ru.landgrafhomyak.utility.IntSerializers;

import java.io.Serializable;
import java.util.Map;

import static kotlin.collections.ArraysKt.associateBy;

/**
 * Варианты оружия ближнего боя для SpaceMarine
 */
public enum MeleeWeapon implements NetObject<MeleeWeapon> {
    CHAIN_SWORD("CHAIN_SWORD"),
    POWER_SWORD("POWER_SWORD"),
    CHAIN_AXE("CHAIN_AXE"),
    MANREAPER("MANREAPER"),
    POWER_BLADE("POWER_BLADE");
    private final String meleeWeaponName;
    private static final Map<String, MeleeWeapon> name2instance = associateBy(MeleeWeapon.values(), v -> v.meleeWeaponName);

    MeleeWeapon(String meleeWeaponName) {
        this.meleeWeaponName = meleeWeaponName;
    }

    public static MeleeWeapon getMeleeWeaponByName(String meleeWeaponName) throws NamingEnumException {
        if (name2instance.get(meleeWeaponName) == null) {
            throw new NamingEnumException("Неправильное имя для оружия ближнего боя");
        }
        return name2instance.get(meleeWeaponName);
    }

    public static final NetObjectSerializer<MeleeWeapon> Serializer = new EnumSerializer<>(EnumIdMapper
            .builder(MeleeWeapon.class)
            .bind(0, CHAIN_SWORD)
            .bind(1, POWER_SWORD)
            .bind(2, CHAIN_AXE)
            .bind(3, MANREAPER)
            .bind(4, POWER_BLADE)
            .build()
    );

    @Override
    public NetObjectSerializer<MeleeWeapon> getSerializer() {
        return MeleeWeapon.Serializer;
    }
}