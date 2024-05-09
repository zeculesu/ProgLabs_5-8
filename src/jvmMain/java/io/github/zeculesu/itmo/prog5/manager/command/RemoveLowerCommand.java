package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Удаление элементов меньше заданного
 */
public class RemoveLowerCommand implements CommandAction {

    boolean acceptsElement = true;
    @Override
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        SpaceMarine elem = element[0];
        try {

            int startSize = collectionSpaceMarine.size();

            collectionSpaceMarine.removeLower(elem);

            int endSize = collectionSpaceMarine.size();
            if (startSize == endSize) {
                response.setError("Элементов с меньше данного в коллекции не найдено");
            }
            response.setMessage("Удаление произошло успешно");
        } catch (NamingEnumException | InputFormException e) {
            response.setError(e.getMessage());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_lower";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
