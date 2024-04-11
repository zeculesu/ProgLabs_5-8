package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Очищение коллекции
 */
public class ClearCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        collectionSpaceMarine.clear();
        response.setMessage("Коллекция очищена");
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "clear";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }

    @Override
    public boolean isAcceptsElement() {
        return this.acceptsElement;
    }
}
