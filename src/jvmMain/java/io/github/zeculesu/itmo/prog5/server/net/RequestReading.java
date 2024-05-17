package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.models.Request;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.server.command.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class RequestReading {
    public static Response requestRead(DatagramPacket receivePacket, ConsoleCommandEnvironment env, SpaceMarineCollection collection) throws IOException, ClassNotFoundException {
        // Преобразуем данные в массив байт
        byte[] data = receivePacket.getData();
        // Преобразуем массив байт обратно в объект
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();

        // Выводим полученное сообщение от клиента на консоль
        System.out.println("Команда с клиента: " + request.getCommand());
        if (request.getCommand().equals("send_command")) {
            CommandAction comm = new SendCommandSet();
            return comm.execute(collection, env, new String[0], null);
        }

        if (request.getCommand().equals("check_uniq_login")) {
            CommandAction comm = new CheckUniqLoginCommand();
            return comm.execute(collection, env, new String[]{request.getArg()}, null);
        }

        if (request.getCommand().equals("register")) {
            CommandAction comm = new RegisterCommand();
            return comm.execute(collection, env, new String[]{request.getArg()}, null);
        }

        if (request.getCommand().equals("auth")) {
            CommandAction comm = new AuthCommand();
            return comm.execute(collection, env, new String[]{request.getArg()}, null);

        }

        env.addCommandToHistory(request.getCommand());
        CommandAction com = env.getCommandSetMap().findCommand(request.getCommand());
        if (com != null) {
            SpaceMarine elem = null;
            if (com.isAcceptsElement()) {
                elem = request.getElem();
            }
            String[] args = new String[1];
            if (com.isAcceptsArg()) {
                args[0] = request.getArg();
            }
            return com.execute(collection, env, args, elem);
        }
        return new Response();
    }
}
