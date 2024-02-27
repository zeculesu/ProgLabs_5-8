package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.error.EmptyCollectionException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

public class PrintHealthCommand implements CommandAction {
    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        try{
            response.addLineOutput(collectionSpaceMarine.printFieldDescendingHealth());
        }
        catch (EmptyCollectionException e){
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "print_field_descending_health";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "print_field_descending_health : вывести значения поля health всех элементов в порядке убывания";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
