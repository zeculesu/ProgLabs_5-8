package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class ClearCommand implements CommandAction {

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
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
