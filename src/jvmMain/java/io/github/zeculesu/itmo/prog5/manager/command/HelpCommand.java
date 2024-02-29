package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

/**
 * Вывод всех возможных команд
 */
public class HelpCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        response.addLineOutput("Список доступных команд:");
        for (CommandAction command : env.getCommandSetMap()) {
            response.addLineOutput(command.getDescription());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "help";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
