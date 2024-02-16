package io.github.zeculesu.itmo.prog5.manager;

import java.util.List;

public interface ConsoleCommandEnvironment {

    CommandSet getCommandSetMap();
    void addCommandToHistory(String command);
    List<String> getCommandHistory();
}
