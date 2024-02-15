package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.user_interface.CommandIO;
import org.jetbrains.annotations.NotNull;

public class FilterNameCommand  implements CommandAction{

    @Override
    public String execute(SpaceMarineCollection collectionSpaceMarine, CommandIO console, String[] args) {
        if (args.length == 0){
            return "Не введен аргумент - подстрока, с которой должны начинаться имена элементов";
        }
        String name = args[0];
        for (SpaceMarine elem : collectionSpaceMarine){
            if (elem.getName().startsWith(name)){
                console.print(elem.toString());
            }
        }
        return "...";
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
