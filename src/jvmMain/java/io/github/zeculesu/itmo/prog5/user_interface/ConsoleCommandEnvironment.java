package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.manager.CommandSet;

import java.io.BufferedReader;
import java.util.List;
import java.util.Set;

/**
 * Окружение для работы
 */
public interface ConsoleCommandEnvironment {

    String getFileNameCollection();

    CommandSet getCommandSetMap();

    void addCommandToHistory(String command);

    List<String> getCommandHistory();

    boolean isRun();

    void setRun(boolean stage);

    public StateIO getStateIO();

    public void setStateIO(StateIO stateIO);

    public BufferedReader getBufferReaderScript();

    public void setBufferReaderScript(BufferedReader bufferedReader);

    public boolean checkRecursionScript(String fileName);

    public void addScriptQueue(String scriptName);

    public void clearScriptQueue();
}
