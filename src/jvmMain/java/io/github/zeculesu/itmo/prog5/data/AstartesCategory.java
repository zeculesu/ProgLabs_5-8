package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.error.NamingEnumException;

import java.util.Map;

import static kotlin.collections.ArraysKt.associateBy;


public enum AstartesCategory {
    SCOUT("SCOUT"),
    SUPPRESSOR("SUPPRESSOR"),
    LIBRARIAN("LIBRARIAN"),
    HELIX("HELIX");

    private final String astartesCategoryName;
    private static final Map<String, AstartesCategory> name2instance = associateBy(AstartesCategory.values(), v -> v.astartesCategoryName);
//
//    static {
//        Map<String, AstartesCategory> names = new HashMap<>();
//        for (AstartesCategory cat : AstartesCategory.values()) {
//            names.put(cat.astartesCategoryName, cat);
//        }
//        name2instance = names;
//    }

    AstartesCategory(String astartesCategoryName) {
        this.astartesCategoryName = astartesCategoryName;
    }

    public static AstartesCategory getCategoryByName(String astartesCategoryName) throws NamingEnumException{
        if (name2instance.get(astartesCategoryName) == null){
            throw new NamingEnumException("Неправильное имя для категория");
        }
        return name2instance.get(astartesCategoryName);
    }
}
