package io.github.zeculesu.itmo.prog5.user_interface;

import java.util.ArrayList;

import static kotlin.io.ConsoleKt.readlnOrNull;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.DefaultConsoleCommandEnvironmentImpl;


public class Console implements CommunicatedClient {

    //CommandSetMap commandSetMap;
    private DefaultConsoleCommandEnvironmentImpl environment;

    private ArrayList<String> commandlist = new ArrayList<>();
    private SpaceMarineCollection collectionSpaceMarine;

    //в Main'e будет DefaultConsoleCommandEnvironmentImpl(CommandSetMap)

    public Console(DefaultConsoleCommandEnvironmentImpl environment, SpaceMarineCollection collectionSpaceMarine) {
        this.environment = environment;
        this.collectionSpaceMarine = collectionSpaceMarine;
    }

    @Override
    public void run() {
        String command;
        boolean stop = false;
        while (!stop) {
            command = readlnOrNull();
            readCommand(command);
        }
    }

    public void readCommand(String command) {
        String[] token = command.split(" ");
        this.commandlist.add(token[0]);
        CommandAction com = this.environment.getCommandSetMap().findCommand(token[0]);
        if (com != null) {
            if (token.length == 2){
                String[] args = token[1].split(";");
                com.execute(this.collectionSpaceMarine, this.new CommandIOImpl(), this.environment, args);
            }
            else {
                String[] args = new String[0];
                com.execute(this.collectionSpaceMarine, this.new CommandIOImpl(), this.environment, args);
            }
        } else System.out.println("Ебанат, ты что пишешь вообще, это не команда"); //todo УБРАТь
    }

    class CommandIOImpl implements CommandIO{

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
