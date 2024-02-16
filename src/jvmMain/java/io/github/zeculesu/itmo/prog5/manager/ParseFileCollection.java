package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;

public interface ParseFileCollection {
    public static CollectionAction parseFile(String filePath, CollectionAction collection){
        return collection;
    }
    public static void writeFile(String filePath, CollectionAction collectionAction){}
}
