package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandAction {


    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        // Вставить в Main
//        CommandSetMap commandSetMap = new CommandSetMap(new AddCommand(), new ClearCommand(), new FilterNameCommand(), new HelpCommand(), new PrintHealthCommand(), new RemoveByIdCommand(), new RemoveByWeaponCommand(), new RemoveHeadCommand(), new RemoveLowerCommand(),
//                new SaveCommand(), new ShowCommand(), new UpdateCommand());
//        List<CommandAction> commandSet = kotlin.collections.CollectionsKt.arrayListOf(new AddCommand(), new ClearCommand(), new FilterNameCommand(),
//                new PrintHealthCommand(), new RemoveByIdCommand(), new RemoveByWeaponCommand(), new RemoveHeadCommand(), new RemoveLowerCommand(),
//                new SaveCommand(), new ShowCommand(), new UpdateCommand());
//        HelpCommand helpCommand = new HelpCommand(commandSet);
//        commandSet.add(helpCommand);
//        CommandSetMap commandSetMap = new CommandSetMap((CommandAction) commandSet);

        for (CommandAction command : env.getCommandSetMap()) {
            console.println(command.getDescription());
        }
        return "...";
    }

    @NotNull
    @Override
    public String getName() {
        return "help";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
