package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.server.command.DownloadCollectionCommand;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private final int port;
    private final ConsoleCommandEnvironment environment;
    private final CollectionAction collectionSpaceMarine;

    private final byte[] receiveData = new byte[65507];

    public Server(ConsoleCommandEnvironment environment, CollectionAction collectionSpaceMarine, int port){
        this.environment = environment;
        this.collectionSpaceMarine = collectionSpaceMarine;
        this.port = port;
    }

    public void run() {
        System.out.println("Начало работы сервера");
        this.environment.setRun(true);

        //загрузка коллекции из файла
        new DownloadCollectionCommand().execute(this.collectionSpaceMarine, environment, new String[0]);
        System.out.println("ok");

        try {
            // Создаем сокет для приема данных на порту
            DatagramSocket serverSocket = new DatagramSocket(port);
            // Создаем буфер для приема данных от клиента

            while (true) {
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
}
