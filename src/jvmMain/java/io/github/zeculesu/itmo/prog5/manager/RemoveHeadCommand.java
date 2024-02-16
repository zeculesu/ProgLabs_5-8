package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class RemoveHeadCommand implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        return collectionSpaceMarine.remove_head(console);
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_head";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "remove_head : вывести первый элемент коллекции и удалить его";
    }
}
