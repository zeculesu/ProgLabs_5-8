package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;

public class RemoveLowerCommand implements CommandAction{
    @Override
    public void execute() {

    }

    @NotNull
    @Override
    public String getName() {
        return "remove_lower";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
