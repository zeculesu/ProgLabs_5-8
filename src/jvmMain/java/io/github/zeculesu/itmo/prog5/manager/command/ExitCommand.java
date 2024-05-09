package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Завершение работы
 */
public class ExitCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
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
