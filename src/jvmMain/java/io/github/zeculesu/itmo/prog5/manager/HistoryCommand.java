package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoryCommand implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        List<String> history = env.getCommandHistory();
        int st = Math.max(history.size() - 13, 0);
        for (int i = 0; (i < 13 && i < history.size()); i++) {
            console.println(history.get(st + i));
        }
        return null;
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
}
