package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;

/**
 * Парсинг коллекции из файла
 */
public interface ParseFileCollection {
    public static SpaceMarineCollection parseFile(String filePath, SpaceMarineCollection collection){
        return collection;
    }
    public static void writeFile(String filePath, SpaceMarineCollection spaceMarineCollection){}
}
