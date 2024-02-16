package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import org.jetbrains.annotations.NotNull;

public class FilterNameCommand  implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0){
            return "Не введен аргумент - подстрока, с которой должны начинаться имена элементов";
        }
        String name = args[0];
        return collectionSpaceMarine.filter_starts_with_name(name, console);
    }

    @NotNull
    @Override
    public String getName() {
        return "filter_starts_with_name";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
