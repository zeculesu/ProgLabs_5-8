package io.github.zeculesu.itmo.prog5;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.manager.CommandSet;
import io.github.zeculesu.itmo.prog5.manager.CommandSetMapImpl;
import io.github.zeculesu.itmo.prog5.manager.command.*;
import io.github.zeculesu.itmo.prog5.user_interface.CommunicatedClient;
import io.github.zeculesu.itmo.prog5.user_interface.Console;
import io.github.zeculesu.itmo.prog5.user_interface.DefaultConsoleCommandEnvironmentImpl;


public class Main {
    public static void main(String[] args) {
        CommandSet commandSetMap = new CommandSetMapImpl(new AddCommand(), new ClearCommand(), new FilterNameCommand(),
                new HelpCommand(), new PrintHealthCommand(), new RemoveByIdCommand(), new RemoveByWeaponCommand(), new RemoveHeadCommand(), new RemoveLowerCommand(),
                new SaveCommand(), new ShowCommand(), new UpdateCommand(), new InfoCommand(), new ExitCommand(), new HistoryCommand(), new ExecuteScriptCommand());
        String fileName = System.getenv("FILENAME");
        DefaultConsoleCommandEnvironmentImpl env = new DefaultConsoleCommandEnvironmentImpl(commandSetMap, fileName);
        SpaceMarineCollection collectionSpaceMarine = new SpaceMarineCollection();
        System.out.println("Файл с коллекцией:");
        System.out.println(collectionSpaceMarine.load(fileName));
        System.out.println("...");
        CommunicatedClient console = new Console(env, collectionSpaceMarine);
        console.run();
    }
}