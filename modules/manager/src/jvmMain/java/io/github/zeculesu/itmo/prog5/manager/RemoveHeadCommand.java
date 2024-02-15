package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;

public class RemoveHeadCommand implements CommandAction{

    @Override
    public void execute() {

    }

    @NotNull
    @Override
    public String getName() {
        return "remove_head";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции и удалить его";
    }
}
