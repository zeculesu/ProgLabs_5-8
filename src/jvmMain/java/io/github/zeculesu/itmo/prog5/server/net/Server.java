package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.server.command.DownloadCollectionCommand;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static io.github.zeculesu.itmo.prog5.client.ColorConsole.ERROR;

public class Server {
    private final int port;
    private final ConsoleCommandEnvironment environment;
    private final CollectionAction collectionSpaceMarine;

    private final byte[] receiveData = new byte[65507];

    public Server(ConsoleCommandEnvironment environment, CollectionAction collectionSpaceMarine, int port) {
        this.environment = environment;
        this.collectionSpaceMarine = collectionSpaceMarine;
        this.port = port;
    }

    public void run() {
        System.out.println("Начало работы сервера");
        this.environment.setRun(true);

        //загрузка коллекции из файла
        output(new DownloadCollectionCommand().execute(this.collectionSpaceMarine, environment, new String[0]));

        try {
            // Создаем сокет для приема данных на порту
            DatagramSocket serverSocket = new DatagramSocket(port);
            // Создаем буфер для приема данных от клиента

            while (this.environment.isRun()) {
                // получаем запрос от клиента
                DatagramPacket receivePacket = ConnectionReception.reception(serverSocket, this.receiveData);

                // Выполняем запрос клиента
                Response response = RequestReading.requestRead(receivePacket, this.environment, this.collectionSpaceMarine);

                //отправляем ответ клиенту
                ResponseSending.responseSend(serverSocket, receivePacket, response);

//                if (!this.environment.isRun()){
//                    //сохранение коллекции в файл после завершения работы клиента
//                    outputResponse(new SaveCommand().execute(this.collectionSpaceMarine, environment, new String[0]));
//                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void output(Response response) {
        if (response.isOutputElement()) {
            for (SpaceMarine line : response.getOutputElement()) {
                System.out.println(line.toString());
            }
        }
        if (response.isOutput()) {
            for (String line : response.getOutput()) {
                System.out.println(line);
            }
        }
        if (response.isError()) System.out.println(response.getError());
        if (response.isMessage()) System.out.println(response.getMessage());
    }
}
