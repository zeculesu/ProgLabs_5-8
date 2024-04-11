package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.server.command.CommandAction;
import io.github.zeculesu.itmo.prog5.server.command.CommandSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Множество команд, методы для работы с ними
 */
public class CommandSetMapImpl implements CommandSet {

    HashMap<String, CommandAction> commandSet = new HashMap<>();

    public CommandSetMapImpl(CommandAction... commands){
        //заполняется в io.github.zeculesu.itmo.prog5.Main
        for (CommandAction command : commands){
            this.commandSet.put(command.getName(), command);
        }
    }

    @Nullable
    @Override
    public CommandAction findCommand(@NotNull String comm) {
        return this.commandSet.get(comm);
    }

    @NotNull
    @Override
    public Iterator<CommandAction> iterator() {
        return this.commandSet.values().iterator();
    }
}
