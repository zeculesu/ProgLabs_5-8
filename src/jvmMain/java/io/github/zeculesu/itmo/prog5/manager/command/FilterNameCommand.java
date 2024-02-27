package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

public class FilterNameCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        if (args.length == 0){
            response.setMessage("Не введен аргумент - подстрока, с которой должны начинаться имена элементов");
            return response;
        }
        String name = args[0];
        response.setOutputElement(collectionSpaceMarine.filterStartsWithName(name));
        if (!response.isOutputElement()) response.setMessage("Не нашлось ни одного элемента");
        return response;
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

    @Override
    public boolean isAcceptsElement() {
        return this.acceptsElement;
    }
}
