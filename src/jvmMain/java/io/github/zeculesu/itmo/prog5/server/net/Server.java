package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.data.Response;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    static int port = 9876;
    public static void main(String[] args) {
        System.out.println("Начало работы сервера");
        try {
            // Создаем сокет для приема данных на порту
            DatagramSocket serverSocket = new DatagramSocket(port);
            // Создаем буфер для приема данных от клиента
            byte[] receiveData = new byte[1024];

            while (true) {
                // получаем запрос от клиента
                DatagramPacket receivePacket = ConnectionReception.reception(serverSocket, receiveData);

                // Выполняем запрос клиента
                Response response = RequestReading.requestRead(receivePacket);

                //отправляем ответ клиенту
                ResponseSending.responseSend(serverSocket, receivePacket, response);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
