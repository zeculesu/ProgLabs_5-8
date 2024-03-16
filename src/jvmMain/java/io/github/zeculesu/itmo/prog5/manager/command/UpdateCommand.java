package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.ElementNotFound;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

/**
 * Обновление полей элемента по его id
 */
public class UpdateCommand implements CommandAction {

    boolean acceptsElement = true;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        if (args.length == 0) {
            response.setError("Не введен аргумент - id элемента");
            return response;
        }
        SpaceMarine elem = element[0];
        try {
            int id = ElementFormConsole.checkId(args[0]);
            collectionSpaceMarine.update(id, elem);
            response.setMessage("Элемент обновлен");
        } catch (NamingEnumException | InputFormException | ElementNotFound e) {
            response.setError(e.getMessage());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "update";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
