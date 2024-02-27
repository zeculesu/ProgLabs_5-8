package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExecuteScriptCommand implements CommandAction {

    boolean acceptsElement = false;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        if (args.length == 0) {
            response.setMessage("имя файла не введено");
            return response;
        }
        try {
            //todo проверка на расширение файла
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            env.setStartScript(true);
            env.setBufferReaderScript(bufferedReader);
            response.addLineOutput("Начало выполнения скрипта: ");
        } catch (FileNotFoundException e) {
            response.setMessage("Не удалось открыть файл");
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
