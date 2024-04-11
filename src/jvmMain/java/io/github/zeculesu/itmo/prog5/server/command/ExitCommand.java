package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Завершение работы
 */
public class ExitCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        env.setRun(false);
        response.setMessage("Конец работы программы");
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "exit";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }


    @Override
    public boolean isAcceptsElement() {
        return this.acceptsElement;
    }
}
