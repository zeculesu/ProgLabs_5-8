package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SendedCommandResponse;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;

public class SendCommandSet extends AbstractCommand {

    public SendCommandSet() {
        super("send_command", "send_command: на клиент присылаются все доступные команды", false, false);
    }

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        //todo отправка команд
        SendedCommandResponse commands = new SendedCommandResponse();
        for (CommandAction comm : env.getCommandSetMap()){
            commands.addData(comm.getName(), comm.isAcceptsElement(), comm.isAcceptsArg());
        }
        return commands;
    }
}
