package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.data.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ResponseSending {
    public static void responseSend(DatagramSocket serverSocket, DatagramPacket receivePacket, Response response) throws IOException {
        // Отправка ответа клиенту (в данном случае просто эхо)
        // Получаем IP адрес и порт клиента, чтобы отправить ответ по тому же каналу
        InetAddress clientIPAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        // Создаем сообщение для отправки клиенту
        String responseMessage = "Hello, client!";
        // Преобразуем сообщение в массив байтов
        byte[] sendData = responseMessage.getBytes();
        // Создаем пакет для отправки данных клиенту
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
        // Отправляем пакет клиенту
        serverSocket.send(sendPacket);
    }
}
