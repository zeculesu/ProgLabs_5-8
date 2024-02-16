package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.manager.CommandSet;

import java.util.List;

public interface ConsoleCommandEnvironment {

    String getFileNameCollection();

    CommandSet getCommandSetMap();

    void addCommandToHistory(String command);

    List<String> getCommandHistory();

    boolean isStage();

    void setStage(boolean stage);
}
