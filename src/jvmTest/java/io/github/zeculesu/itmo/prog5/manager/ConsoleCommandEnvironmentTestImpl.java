package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.StateIO;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleCommandEnvironmentTestImpl implements ConsoleCommandEnvironment {

    private CommandSet commandSetMap;
    private List<String> commandHistory = new ArrayList<>();

    public ConsoleCommandEnvironmentTestImpl(CommandSet commandSet){
        this.commandSetMap = commandSet;
    }

    @Override
    public String getFileNameCollection() {
        return null;
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

    @Override
    public boolean isRun() {
        return false;
    }

    @Override
    public void setRun(boolean stage) {

    }

    @Override
    public StateIO getStateIO() {
        return null;
    }

    @Override
    public void setStateIO(StateIO stateIO) {

    }

    @Override
    public BufferedReader getBufferReaderScript() {
        return null;
    }

    @Override
    public void setBufferReaderScript(BufferedReader bufferedReader) {

    }

    @Override
    public boolean checkRecursionScript(String fileName) {
        return false;
    }

    @Override
    public void addScriptQueue(String scriptName) {

    }

    @Override
    public void clearScriptQueue() {

    }
}
