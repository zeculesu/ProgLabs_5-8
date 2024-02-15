package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.user_interface.CommandIO;
import org.jetbrains.annotations.NotNull;

public class RemoveByIdCommand implements CommandAction{

    @Override
    public String execute(SpaceMarineCollection collectionSpaceMarine, CommandIO console, DefaultConsoleCommandEnvironmentImpl env, String[] args) {
        return null;
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
}
