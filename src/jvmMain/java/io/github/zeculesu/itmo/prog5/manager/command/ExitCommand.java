package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class ExitCommand implements CommandAction {

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        env.setStage(false);
        return "Конец работы программы";
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
}
