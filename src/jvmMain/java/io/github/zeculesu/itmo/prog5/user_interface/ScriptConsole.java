package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.manager.Response;
import org.jetbrains.annotations.NotNull;


//import static kotlin.text.StringsKt.isBlank;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static kotlin.io.ConsoleKt.readlnOrNull;


public class ScriptConsole implements CommunicatedClient {

    private final ConsoleCommandEnvironment environment;
    private final CollectionAction collectionSpaceMarine;
    private String fileInput;
    BufferedReader bufferedReader;


    public ScriptConsole(ConsoleCommandEnvironment environment, CollectionAction collectionSpaceMarine, String fileInput) {
        this.environment = environment;
        this.environment.setStage(true);
        this.collectionSpaceMarine = collectionSpaceMarine;
        this.fileInput = fileInput;
    }

    @Override
    public void run() {
        try {
            FileReader fileReader = new FileReader(fileInput);
            this.bufferedReader = new BufferedReader(fileReader);
            String command;
            while (bufferedReader.ready()) {
                System.out.print("> ");
                command = bufferedReader.readLine();
                if (command == null) {
                    System.out.println("Конец работы программы");
                    return;
                } else if (command.isBlank()) {
                    System.out.println("Команда не введена");
                } else {
                    readCommand(command);
                }
            }
        } catch (IOException e) {
            System.out.println("файл не найден");
        }
    }

    public void readCommand(@NotNull String command) {
        String[] token = command.split(" ");
        CommandAction com = this.environment.getCommandSetMap().findCommand(token[0]);
        if (com != null) {
            //todo доделать реализацию множественных аргументов
            String[] args = token.length == 2 ? token[1].split(";") : new String[0];
            if (com.isAcceptsElement()) {
                try {
                    ElementFormConsole element = new ElementFormConsole(new CommandIOImpl(bufferedReader));
                    Response response = com.execute(this.collectionSpaceMarine, this.environment, args, element);
                    outputResponse(response);
                } catch (NullPointerException e) {
                    this.environment.setStage(false);
                } catch (InputFormException | NamingEnumException | IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Response response = com.execute(this.collectionSpaceMarine, this.environment, args);
                outputResponse(response);
            }
        } else System.out.println("Неизвестная команда. Введите 'help' для получения справки.");
        this.environment.addCommandToHistory(token[0]);
    }

    public void outputResponse(Response response) {
        if (response.isOutputElement()) {
            for (SpaceMarine line : response.getOutputElement()) {
                System.out.println(line);
            }
        }
        if (response.isOutput()) {
            for (String line : response.getOutput()) {
                System.out.println(line);
            }
        }
        if (response.isMessage()) System.out.println(response.getMessage());
    }

    class CommandIOImpl implements CommandIO {
        BufferedReader bufferedReader;
        public  CommandIOImpl(BufferedReader bufferedReader){
            this.bufferedReader = bufferedReader;
        }

        @Override
        public void print(String line) {
            System.out.print(line);
        }

        @Override
        public void println(String line) {
            System.out.println(line);
        }

        @Override
        public String readln() throws IOException {
            return this.bufferedReader.readLine();
        }
    }
}
