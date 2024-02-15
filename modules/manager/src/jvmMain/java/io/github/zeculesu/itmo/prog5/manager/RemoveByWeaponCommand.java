package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.user_interface.CommandIO;
import org.jetbrains.annotations.NotNull;

public class RemoveByWeaponCommand implements CommandAction{


    @Override
    public String execute(SpaceMarineCollection collectionSpaceMarine, CommandIO console, DefaultConsoleCommandEnvironmentImpl env, String[] args) {
        return null;
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_all_by_melee_weapon";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, значение поля meleeWeapon которого эквивалентно заданному";
    }
}
