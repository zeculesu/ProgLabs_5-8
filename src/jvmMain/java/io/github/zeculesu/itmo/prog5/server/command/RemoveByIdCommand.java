package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.ElementNotFound;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;

/**
 * Удаление элемента из коллекции по его id
 */
public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand(){
        super("remove_by_id", "remove_by_id id : удалить элемент из коллекции по его id", false, true);
    }

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        if (args.length == 0){
            response.setError("Не введен аргумент - id элемента для удаления");
            return response;
        }
        try{
            int id = Integer.parseInt(args[0]);
            collectionSpaceMarine.removeById(id);
        }
        catch (ElementNotFound e){
            response.setError(e.getMessage());
        }
        catch (NumberFormatException e) {
            response.setError("Неверный формат ввода id");
        }
        return response;
    }
}
