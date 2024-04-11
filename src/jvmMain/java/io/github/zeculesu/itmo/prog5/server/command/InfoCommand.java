package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Получение информации о коллекции
 */
public class InfoCommand implements CommandAction {

    boolean acceptsElement = false;
    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        response.setOutput(collectionSpaceMarine.info());
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "info";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}

