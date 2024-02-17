package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandAction {


    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        for (CommandAction command : env.getCommandSetMap()) {
            console.println(command.getDescription());
        }
        return "...";
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
}
