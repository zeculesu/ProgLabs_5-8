package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.EmptyCollectionException;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Получение всех полей здоровья из коллекции
 */
public class PrintHealthCommand implements CommandAction {
    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        try{
            response.addLineOutput(collectionSpaceMarine.printFieldDescendingHealth());
        }
        catch (EmptyCollectionException e){
            response.setError(e.getMessage());
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
