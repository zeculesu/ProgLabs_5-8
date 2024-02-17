package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScriptCommand implements CommandAction {
    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0) {
            return "имя файла не введено";
        }
//        try {
//            String scriptName = args[0];
//            FileReader fileReader = new FileReader(scriptName);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            while (bufferedReader.ready()) {
//                stringBuilder.append(bufferedReader.readLine());
//            }
//            return null;
//        } catch (IOException e) {
//            return "файл не найден";
//        }
        return "не сделано(";
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
}
