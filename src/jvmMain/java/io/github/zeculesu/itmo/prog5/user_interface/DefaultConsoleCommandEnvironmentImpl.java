package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.manager.CommandSet;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class DefaultConsoleCommandEnvironmentImpl implements ConsoleCommandEnvironment{

    private boolean stage;
    private boolean startScript;
    private BufferedReader bufferReaderScript;
    private final CommandSet commandSetMap;
    private String fileNameCollection;
    private final List<String> commandHistory = new ArrayList<>();

    public DefaultConsoleCommandEnvironmentImpl(CommandSet commandSetMap, String fileNameCollection){
        this.commandSetMap = commandSetMap;
        this.fileNameCollection = fileNameCollection;
    }

    @Override
    public String getFileNameCollection() {
        return this.fileNameCollection;
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

    public boolean isStage() {
        return stage;
    }

    public void setStage(boolean stage) {
        this.stage = stage;
    }

    public boolean isStartScript() {
        return startScript;
    }

    public void setStartScript(boolean startScript) {
        this.startScript = startScript;
    }

    @Override
    public BufferedReader getBufferReaderScript() {
        return bufferReaderScript;
    }

    @Override
    public void setBufferReaderScript(BufferedReader bufferReaderScript) {
        this.bufferReaderScript = bufferReaderScript;
    }
}
