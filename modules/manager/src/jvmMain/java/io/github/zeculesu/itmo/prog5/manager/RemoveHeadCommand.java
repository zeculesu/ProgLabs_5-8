package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import org.jetbrains.annotations.NotNull;

public class RemoveHeadCommand implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        return null;
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_head";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции и удалить его";
    }
}
