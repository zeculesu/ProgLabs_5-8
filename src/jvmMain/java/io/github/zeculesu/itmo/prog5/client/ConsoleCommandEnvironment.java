package io.github.zeculesu.itmo.prog5.client;

import io.github.zeculesu.itmo.prog5.server.command.CommandSet;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Окружение для работы
 */
public interface ConsoleCommandEnvironment {

    String getFileNameCollection();

    CommandSet getCommandSetMap();

    void removeFromCommandSetMap(String command);

    void addCommandToHistory(String command);

    List<String> getCommandHistory();

    boolean isRun();

    void setRun(boolean stage);

    StateIO getStateIO();

    void setStateIO(StateIO stateIO);

    BufferedReader getBufferReaderScript();

    void setBufferReaderScript(BufferedReader bufferedReader);

    boolean checkRecursionScript(String fileName);

    void addScriptQueue(String scriptName);

    void clearScriptQueue();

    Connection getConnection();

    void setConnection(String url, String username, String password) throws SQLException;
}
