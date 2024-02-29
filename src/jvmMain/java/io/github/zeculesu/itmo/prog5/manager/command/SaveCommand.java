package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.error.FileCollectionException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

/**
 * Сохранение в файл
 */
public class SaveCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        try{
            collectionSpaceMarine.save(env.getFileNameCollection());
            response.setMessage("Сохранение прошло успешно");
        }
        catch (FileCollectionException e){
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "save";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
