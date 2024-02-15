package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;

public class ShowCommand implements CommandAction{
    @Override
    public void execute() {

    }

    @NotNull
    @Override
    public String getName() {
        return "show";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
