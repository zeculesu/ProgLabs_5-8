package io.github.zeculesu.itmo.prog5.manager;

import java.util.List;

public interface ConsoleCommandEnvironment {

    public CommandSet getCommandSetMap();
    public void addCommandToHistory(String command);
    public List<String> getCommandHistory();
}
