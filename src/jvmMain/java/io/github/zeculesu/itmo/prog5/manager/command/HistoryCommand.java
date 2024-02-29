package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Вывод истории команд
 */
public class HistoryCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        List<String> history = env.getCommandHistory();
        int start = Math.max(history.size() - 13, 0);
        for (int i = 0; (i < 13 && i < history.size()); i++) {
            response.addLineOutput(history.get(start + i));
        }
        if (!response.isOutput()) response.setMessage("История команд пуста");
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "history";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "history : вывести последние 13 команд (без их аргументов)";
    }

    @Override
    public boolean isAcceptsElement() {
        return this.acceptsElement;
    }
}
