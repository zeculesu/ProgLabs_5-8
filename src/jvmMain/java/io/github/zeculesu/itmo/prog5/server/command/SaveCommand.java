package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.FileCollectionException;
import io.github.zeculesu.itmo.prog5.server.parseFile.ParseFileXML;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Сохранение в файл
 */
public class SaveCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        try{
            ParseFileXML.writeFile(env.getFileNameCollection(), collectionSpaceMarine);
            response.setMessage("Сохранение прошло успешно");
        }
        catch (FileCollectionException e){
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
