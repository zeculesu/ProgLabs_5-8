package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.user_interface.CommandIO;
import org.jetbrains.annotations.NotNull;

public class PrintHealthCommand implements CommandAction{

    @Override
    public String execute(SpaceMarineCollection collectionSpaceMarine, CommandIO console, DefaultConsoleCommandEnvironmentImpl env, String[] args) {
        return null;
    }

    @NotNull
    @Override
    public String getName() {
        return "print_field_descending_health";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "вывести значения поля health всех элементов в порядке убывания";
    }
}
