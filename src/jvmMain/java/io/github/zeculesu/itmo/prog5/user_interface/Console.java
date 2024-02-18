package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.manager.Response;
import org.jetbrains.annotations.NotNull;


//import static kotlin.text.StringsKt.isBlank;


import static kotlin.io.ConsoleKt.readlnOrNull;


public class Console implements CommunicatedClient {

    private final DefaultConsoleCommandEnvironmentImpl environment;
    private final SpaceMarineCollection collectionSpaceMarine;


    public Console(DefaultConsoleCommandEnvironmentImpl environment, SpaceMarineCollection collectionSpaceMarine) {
        this.environment = environment;
        this.environment.setStage(true);
        this.collectionSpaceMarine = collectionSpaceMarine;
    }

    @Override
    public void run() {
        System.out.println("\u001B[31m" + "Красный текст" + "\u001B[0m");
        String fileName = environment.getFileNameCollection();
        if (fileName == null){
            System.out.println("Имя файла с коллекцией не указано, оно должно храниться в переменной окружения FILENAME");
        }
        else {
            System.out.println("Файл с коллекцией: " + fileName);
            System.out.println(collectionSpaceMarine.load(fileName));
        }

        String command;
        while (environment.isStage()) {
            System.out.print("> ");
            command = readlnOrNull();
            if (command == null) {
                System.out.println("Конец работы программы");
                return;
            } else if (command.isBlank()) {
                System.out.println("Команда не введена");
            } else {
                readCommand(command);
            }
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
                    ElementFormConsole element = new ElementFormConsole(new CommandIOImpl());
                    Response response = com.execute(this.collectionSpaceMarine, this.environment, args, element);
                    outputResponse(response);
                } catch (NullPointerException e) {
                    this.environment.setStage(false);
                } catch (InputFormException | NamingEnumException e) {
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
        @Override
        public void print(String line) {
            System.out.print(line);
        }

        @Override
        public void println(String line) {
            System.out.println(line);
        }

        @Override
        public String readln() {
            return readlnOrNull();
        }
    }
}
