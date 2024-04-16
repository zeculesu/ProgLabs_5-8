package io.github.zeculesu.itmo.prog5;

import io.github.zeculesu.itmo.prog5.client.DefaultConsoleCommandEnvironmentImpl;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.server.command.*;
import io.github.zeculesu.itmo.prog5.server.net.Server;

public class MainServer {
    public static void main(String[] args) {
        // команды доступные для клиента
        CommandSet commandSetMapClient = new CommandSetMapImpl(new AddCommand(), new ClearCommand(), new FilterNameCommand(),
                new HelpCommand(), new PrintHealthCommand(), new RemoveByIdCommand(), new RemoveByWeaponCommand(), new RemoveHeadCommand(), new RemoveLowerCommand(),
                new ShowCommand(), new UpdateCommand(), new InfoCommand(), new ExitCommand(), new HistoryCommand(), new ExecuteScriptCommand());
        String fileName = System.getenv("FILENAME");
        DefaultConsoleCommandEnvironmentImpl env = new DefaultConsoleCommandEnvironmentImpl(commandSetMapClient, fileName);
        SpaceMarineCollection collectionSpaceMarine = new SpaceMarineCollection();
        Server server = new Server(env, collectionSpaceMarine, 45000);
        server.run();
    }
}