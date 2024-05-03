package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.EmptyCollectionException;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;

/**
 * Получение всех элементов коллекции
 */
public class ShowCommand extends AbstractCommand {

    public ShowCommand(){
        super("show", "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении", false, false);
    }

    @Override
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        try{
            response.setOutputElement(collectionSpaceMarine.show());
        }
        catch (EmptyCollectionException e){
            response.setError(e.getMessage());
        }
        return response;
    }
}
