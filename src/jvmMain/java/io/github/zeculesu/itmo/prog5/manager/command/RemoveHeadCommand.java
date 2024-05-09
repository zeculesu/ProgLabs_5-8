package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.error.EmptyCollectionException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Удаление первого элемента из коллекции
 */
public class RemoveHeadCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        try{
            response.addElement(collectionSpaceMarine.removeHead());
            response.setMessage("Элемент успешно удален");
        }
        catch (EmptyCollectionException e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_head";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "remove_head : вывести первый элемент коллекции и удалить его";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
