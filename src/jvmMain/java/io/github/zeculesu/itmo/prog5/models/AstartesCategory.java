package io.github.zeculesu.itmo.prog5.models;

import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.net.EnumIdMapper;
import io.github.zeculesu.itmo.prog5.net.EnumSerializer;
import io.github.zeculesu.itmo.prog5.net.NetObject;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

import java.util.Map;

import static kotlin.collections.ArraysKt.associateBy;

/**
 * Описывает возможные категории для SpaceMarine
 */
public enum AstartesCategory implements NetObject<AstartesCategory> {
    SCOUT("SCOUT"),
    SUPPRESSOR("SUPPRESSOR"),
    LIBRARIAN("LIBRARIAN"),
    HELIX("HELIX");

    private final String astartesCategoryName;
    private static final Map<String, AstartesCategory> name2instance = associateBy(AstartesCategory.values(), v -> v.astartesCategoryName);

    AstartesCategory(String astartesCategoryName) {
        this.astartesCategoryName = astartesCategoryName;
    }

    public static AstartesCategory getCategoryByName(String astartesCategoryName) throws NamingEnumException {
        if (name2instance.get(astartesCategoryName) == null) {
            throw new NamingEnumException("Неправильное имя для категория");
        }
        return name2instance.get(astartesCategoryName);
    }

    public static final NetObjectSerializer<AstartesCategory> Serializer = new EnumSerializer<>(EnumIdMapper
            .builder(AstartesCategory.class)
            .bind(0, SCOUT)
            .bind(1, SUPPRESSOR)
            .bind(2, LIBRARIAN)
            .bind(3, HELIX)
            .build()
    );

    @Override
    public NetObjectSerializer<AstartesCategory> getSerializer() {
        return AstartesCategory.Serializer;
    }
}
