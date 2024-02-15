package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.user_interface.CommandIO;
import org.jetbrains.annotations.NotNull;

public class ClearCommand implements CommandAction{

    @Override
    public String execute(SpaceMarineCollection collectionSpaceMarine, CommandIO console, String[] args) {
        collectionSpaceMarine.clear();
        //todo проверка на ошибки
        return "Коллекция очищена";
    }

    @NotNull
    @Override
    public String getName() {
        return "clear";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}
