package io.github.zeculesu.itmo.prog5.manager;

import java.util.ArrayList;
import java.util.List;

public class DefaultConsoleCommandEnvironmentImpl implements ConsoleCommandEnvironment{

    private CommandSet commandSetMap;
    private List<String> commandHistory = new ArrayList<>();

    public DefaultConsoleCommandEnvironmentImpl(CommandSet commandSetMap){
        this.commandSetMap = commandSetMap;
    }

    @Override
    public CommandSet getCommandSetMap() {
        return this.commandSetMap;
    }

    @Override
    public void addCommandToHistory(String command) {
        this.commandHistory.add(command);
    }

    @Override
    public List<String> getCommandHistory() {
        return this.commandHistory;
    }
}
