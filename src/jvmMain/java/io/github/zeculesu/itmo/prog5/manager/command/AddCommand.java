package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.*;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

/**
 * Добавление элемента SpaceMarine в коллекцию
 */
public class AddCommand implements CommandAction {

    boolean acceptsElement = true;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        SpaceMarine elem = element[0];
        try {
            collectionSpaceMarine.add(elem);
            response.setMessage("Новый элемент добавлен");
            return response;
        } catch (NamingEnumException | InputFormException e) {
            response.setError(e.getMessage());
            return response;
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "add";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public boolean isAcceptsElement() {
        return this.acceptsElement;
    }
}
