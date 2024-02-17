package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;

import static kotlin.text.StringsKt.isBlank;


import static kotlin.io.ConsoleKt.readlnOrNull;


public class Console implements CommunicatedClient {

    private DefaultConsoleCommandEnvironmentImpl environment;
    private SpaceMarineCollection collectionSpaceMarine;


    public Console(DefaultConsoleCommandEnvironmentImpl environment, SpaceMarineCollection collectionSpaceMarine) {
        this.environment = environment;
        this.environment.setStage(true);
        this.collectionSpaceMarine = collectionSpaceMarine;
    }

    @Override
    public void run() {
        String command;

        while (environment.isStage()) {
            command = readlnOrNull();
            if (command == null) {
                System.out.println("Конец работы программы");
                return;
            } else if (command.isBlank()) {
                System.out.println("Команда не введена");
            } else {
                readCommand(command);
               // System.out.println("...");
            }
        }
        System.out.println("Конец работы программы");
    }

    public void readCommand(String command) {
        String[] token = command.split(" ");
        this.environment.addCommandToHistory(token[0]);
        CommandAction com = this.environment.getCommandSetMap().findCommand(token[0]);
        if (com != null) {
            if (token.length == 2) {
                String[] args = token[1].split(";");
                String responce = com.execute(this.collectionSpaceMarine, this.new CommandIOImpl(), this.environment, args);
                System.out.println(responce);
            } else {
                String[] args = new String[0];
                String responce = com.execute(this.collectionSpaceMarine, this.new CommandIOImpl(), this.environment, args);
                System.out.println(responce);
            }
        } else System.out.println("Это не команда, чтобы посмотреть список всех команд напишите help");
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
