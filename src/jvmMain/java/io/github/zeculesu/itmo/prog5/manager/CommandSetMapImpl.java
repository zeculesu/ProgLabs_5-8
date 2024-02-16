package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;

public class CommandSetMapImpl implements CommandSet{

    HashMap<String, CommandAction> commandSet = new HashMap<>();

    public CommandSetMapImpl(CommandAction... commands){
        //заполняется в Main
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
