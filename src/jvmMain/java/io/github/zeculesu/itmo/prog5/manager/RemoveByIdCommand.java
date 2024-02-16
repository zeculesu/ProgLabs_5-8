package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class RemoveByIdCommand implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0){
            return "Не введен аргумент - id элемента для удаления";
        }
        try{
            int id = Integer.parseInt(args[0]);
            return collectionSpaceMarine.remove_by_id(id);
        }
        catch (NumberFormatException e){
            return "Неверный формат ввода id";
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
