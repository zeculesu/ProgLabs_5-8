package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.data.Request;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.server.command.CommandAction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class RequestReading {
    public static Response requestRead(DatagramPacket receivePacket, ConsoleCommandEnvironment env, CollectionAction collection) throws IOException, ClassNotFoundException {
        // Преобразуем данные в массив байт
        byte[] data = receivePacket.getData();
        // Преобразуем массив байт обратно в объект
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();

        // Выводим полученное сообщение от клиента на консоль
        System.out.println("Received from client: " + request.getCommand());
        CommandAction com = env.getCommandSetMap().findCommand(request.getCommand());
        if (com != null) {
            SpaceMarine elem = null;
            if (com.isAcceptsElement()) {
                elem = request.getElem();
            }
            return com.execute(collection, env, request.getArg(), elem);
        }
        return new Response();
    }
}
