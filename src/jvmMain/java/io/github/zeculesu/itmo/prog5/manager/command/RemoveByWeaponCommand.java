package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.MeleeWeapon;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

public class RemoveByWeaponCommand implements CommandAction {

    boolean acceptsElement = false;
    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        if (args.length == 0){
            response.setMessage("Не введен аргумент - оружие ближнего боя");
            return response;
        }
        try{
            MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeaponByName(args[0]);
            int start = collectionSpaceMarine.size();
            collectionSpaceMarine.removeAllByMeleeWeapon(meleeWeapon);
            int end = collectionSpaceMarine.size();
            if (start == end) {
                response.setMessage("Элементов с таким оружием ближнего боя в коллекции не найдено");
            }
            else {
                response.setMessage("Удаление произошло успешно");
            }
        }
        catch (NamingEnumException e){
            response.setMessage(e.getMessage());
        }
        return response;
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

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
