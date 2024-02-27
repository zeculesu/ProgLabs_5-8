package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.manager.CommandSet;

import java.io.BufferedReader;
import java.util.List;

public interface ConsoleCommandEnvironment {

    String getFileNameCollection();

    CommandSet getCommandSetMap();

    void addCommandToHistory(String command);

    List<String> getCommandHistory();

    boolean isStage();

    void setStage(boolean stage);

    public boolean isStartScript();

    public void setStartScript(boolean startScript);

    public BufferedReader getBufferReaderScript();
    public void setBufferReaderScript(BufferedReader bufferedReader);
}
