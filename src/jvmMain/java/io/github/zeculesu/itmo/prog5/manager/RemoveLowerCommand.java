package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class RemoveLowerCommand implements CommandAction{
    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0){
            return "Не введен аргумент - id элемента для удаления";
        }
        try{
            int id = Integer.parseInt(args[0]);
            return collectionSpaceMarine.remove_by_id(id);
        }
        return collectionSpaceMarine.remove_lower(o);
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_lower";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
