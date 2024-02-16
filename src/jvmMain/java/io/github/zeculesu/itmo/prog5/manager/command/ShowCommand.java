package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class ShowCommand implements CommandAction {

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        return collectionSpaceMarine.show(console);
    }

    @NotNull
    @Override
    public String getName() {
        return "show";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
