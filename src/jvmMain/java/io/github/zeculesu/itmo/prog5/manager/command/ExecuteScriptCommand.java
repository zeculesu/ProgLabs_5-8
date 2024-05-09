package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.StateIO;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Выполнение скрипта из файла
 */
public class ExecuteScriptCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        if (args.length == 0) {
            response.setError("Имя файла не введено");
            return response;
        }
        try {
            String fileName = args[0];
            if (env.checkRecursionScript(fileName)){
                response.setMessage("Вы создаете рекурсию из скриптов, ата-та");
                return response;
            }
            env.addScriptQueue(fileName);
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            env.setBufferReaderScript(bufferedReader);
            response.addLineOutput("Начало выполнения скрипта: ");
            env.setStateIO(StateIO.CONSOLE_TO_SCRIPT);
        } catch (FileNotFoundException e) {
            response.setError("Не удалось открыть файл");
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "execute_script";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
