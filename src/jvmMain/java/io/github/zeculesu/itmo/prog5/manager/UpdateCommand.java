package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import org.jetbrains.annotations.NotNull;

public class UpdateCommand implements CommandAction{
    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0){
            return "Не введен аргумент - id элемента для обновления";
        }
        try{
            int id = Integer.parseInt(args[0]);

            //collectionSpaceMarine.update();
        }
        catch (NumberFormatException e){
            return "Неверный формат ввода id";
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "update";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
}
