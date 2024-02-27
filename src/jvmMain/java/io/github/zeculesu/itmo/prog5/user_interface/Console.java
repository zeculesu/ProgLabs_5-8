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


import java.io.IOException;

import static kotlin.io.ConsoleKt.readlnOrNull;


public class Console implements CommunicatedClient {

    //todo добавить проверку на рекурсию в скриптах

    private final DefaultConsoleCommandEnvironmentImpl environment;
    private final SpaceMarineCollection collectionSpaceMarine;

    private CommandIOConsole console;

    public Console(DefaultConsoleCommandEnvironmentImpl environment, SpaceMarineCollection collectionSpaceMarine) {
        this.environment = environment;
        this.environment.setStage(true);
        this.collectionSpaceMarine = collectionSpaceMarine;
        this.console = new CommandIOConsole();
    }

    @Override
    public void run() {
        // todo разукрасить текст
        // System.out.println("\u001B[31m" + "Красный текст" + "\u001B[0m");

        loadFile(this.environment.getFileNameCollection());

        String command;

        while (environment.isStage()) {

            console.print("> ");

            command = readlnOrNullCommand();

            if (this.environment.getStartScript() == 3) {
                console.println("Конец скрипта");
                this.environment.setStartScript(0);
            } else if (command == null) {
                console.println("Конец работы программы");
                return;
            } else if (command.isBlank()) {
                console.println("Команда не введена");
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
                    ElementFormConsole element = new ElementFormConsole(new CommandIOConsole());
                    Response response = com.execute(this.collectionSpaceMarine, this.environment, args, element);
                    outputResponse(response);
                } catch (NullPointerException e) {
                    this.environment.setStage(false);
                } catch (InputFormException | NamingEnumException | IOException e) {
                    console.println(e.getMessage());
                }
            } else {
                Response response = com.execute(this.collectionSpaceMarine, this.environment, args);
                outputResponse(response);
            }
        } else console.println("Неизвестная команда. Введите 'help' для получения справки.");
        this.environment.addCommandToHistory(token[0]);
        if (this.environment.getStartScript() == 1){
            this.environment.setStartScript(2);
        }
    }

    public void outputResponse(Response response) {
        if (response.isOutputElement()) {
            for (SpaceMarine line : response.getOutputElement()) {
                console.println(line.toString());
            }
        }
        if (response.isOutput()) {
            for (String line : response.getOutput()) {
                console.println(line);
            }
        }
        if (response.isMessage()) console.println(response.getMessage());
    }

    public String readlnOrNullCommand() {
        //todo переделать на Enum состояния скрипта
        if (this.environment.getStartScript() == 2) {
            try {
                if (!this.environment.getBufferReaderScript().ready()) {
                    this.environment.setStartScript(3);
                    console.println("");
                    return null;
                }
                return console.readln();
            } catch (IOException e) {
                console.println("Проблемы с чтением файла скрипта");
            }
        }
        return readlnOrNull();
    }

    public void loadFile(String fileName) {
        if (fileName == null) {
            console.println("Имя файла с коллекцией не указано, оно должно храниться в переменной окружения FILENAME");
        } else {
            console.println("Файл с коллекцией: " + fileName);
            //todo переделать вывод загрузки файла на RESPONSE
            console.println(collectionSpaceMarine.load(fileName));
        }
    }

    class CommandIOConsole implements CommandIO {
        @Override
        public void print(String line) {
            System.out.print(conversionForScriptOutput(line));
        }

        @Override
        public void println(String line) {
            System.out.println(conversionForScriptOutput(line));
        }

        @Override
        public String readln() throws IOException {
            if (Console.this.environment.getStartScript() == 2) {
                String input = Console.this.environment.getBufferReaderScript().readLine();
                System.out.println(input);
                return input;
            }
            return readlnOrNull();
        }

        public String conversionForScriptOutput(String line) {
            if (Console.this.environment.getStartScript() == 2) {
                return "\t" + line;
            }
            return line;
        }
    }
}
