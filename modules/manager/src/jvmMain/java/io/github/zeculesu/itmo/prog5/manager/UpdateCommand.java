package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;

public class UpdateCommand implements CommandAction{
    @Override
    public void execute() {

    }

    @NotNull
    @Override
    public String getName() {
        return "update";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
}
