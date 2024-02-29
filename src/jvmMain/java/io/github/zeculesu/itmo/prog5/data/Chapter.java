package io.github.zeculesu.itmo.prog5.data;

/**
 * Класс описывающий главу для SpaceMarine
 */
public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;

    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    public String getName() {
        return name;
    }

    public String getParentLegion() {
        return parentLegion;
    }

}
