package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.MeleeWeapon;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Удаление элемента из коллекции по его оружию ближнего боя
 */
public class RemoveByWeaponCommand implements CommandAction {

    boolean acceptsElement = false;
    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        if (args.length == 0){
            response.setError("Не введен аргумент - оружие ближнего боя");
            return response;
        }
        try{
            MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeaponByName(args[0]);
            int start = collectionSpaceMarine.size();
            collectionSpaceMarine.removeAllByMeleeWeapon(meleeWeapon);
            int end = collectionSpaceMarine.size();
            if (start == end) {
                response.setError("Элементов с таким оружием ближнего боя в коллекции не найдено");
            }
            else {
                response.setError("Удаление произошло успешно");
            }
        }
        catch (NamingEnumException e){
            response.setError(e.getMessage());
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
