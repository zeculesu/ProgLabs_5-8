package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.MeleeWeapon;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class RemoveByWeaponCommand implements CommandAction {


    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0){
            return "Не введен аргумент - оружие ближнего боя";
        }
        try{
            MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeaponByName(args[0]);
            return collectionSpaceMarine.remove_all_by_melee_weapon(meleeWeapon);
        }
        catch (NamingEnumException e){
            return e.getMessage();
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_all_by_melee_weapon";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "remove_all_by_melee_weapon meleeWeapon : удалить из коллекции все элементы, значение поля meleeWeapon которого эквивалентно заданному";
    }
}
