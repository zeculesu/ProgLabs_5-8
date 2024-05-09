package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.error.FileCollectionException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.ParseFileXML;
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
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        try {
            //todo
            ParseFileXML.writeFile(env.getFileNameCollection(), (SpaceMarineCollection) collectionSpaceMarine);
            response.setMessage("Сохранение прошло успешно");
        } catch (FileCollectionException e) {
            response.setError(e.getMessage());
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
