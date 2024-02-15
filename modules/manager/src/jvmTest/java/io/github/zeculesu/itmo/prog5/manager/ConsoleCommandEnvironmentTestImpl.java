package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.manager.CommandSet;
import io.github.zeculesu.itmo.prog5.manager.ConsoleCommandEnvironment;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCommandEnvironmentTestImpl implements ConsoleCommandEnvironment {

    private CommandSet commandSetMap;
    private List<String> commandHistory = new ArrayList<>();

    public ConsoleCommandEnvironmentTestImpl(CommandSet commandSet){
        this.commandSetMap = commandSet;
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
