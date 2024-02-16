package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import org.jetbrains.annotations.NotNull;

public class PrintHealthCommand implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        return collectionSpaceMarine.print_field_descending_health();
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
