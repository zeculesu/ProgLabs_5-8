package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;

public class SaveCommand implements CommandAction{
    @Override
    public void execute() {

    }

    @NotNull
    @Override
    public String getName() {
        return "save";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}
