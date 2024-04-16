package io.github.zeculesu.itmo.prog5.client;

import io.github.zeculesu.itmo.prog5.models.Request;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SendedCommandResponse;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    DatagramSocket clientSocket;
    InetAddress serverIPAddress;
    String host;
    int port;

    public UDPClient(String host, int port) {
        this.host = host;
        this.port = port;
        //todo Сделать изменяемый this.port = 9876;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            sendMeCommand();
            while (true) {
//                createSocket();
//
//                // Отправка сообщения серверу
//                // Создаем сообщение для отправки
//                String message = scanner.nextLine();
//                // Преобразуем сообщение в массив байтов
//                byte[] sendData = message.getBytes();
//
//                sendPacket(sendData);
//
//                try {
//                    Response response = getResponse();
//                    // Выводим полученное сообщение от сервера на консоль
//                    System.out.println("Received from server: " + response.getMessage() + " " + response.getError() + " " + response.getOutput());
//                } catch (SocketTimeoutException e) {
//                    // Обработка ситуации, когда ответ не был получен вовремя
//                    System.out.println("Сервер временно недоступен");
//                }
//
//                // Закрываем сокет
//                clientSocket.close();
            }
        } catch (Exception e) {
            run();
        }
    }

    public SendedCommandResponse sendMeCommand() throws IOException, ClassNotFoundException, SocketTimeoutException {
        createSocket();

        // Отправка сообщения серверу
        Request request = new Request();
        request.setCommand("send_command");

        byte[] sendData = castToByte(request);

        sendPacket(sendData);

//        try {
//            Response response = getResponse();
//            // Выводим полученное сообщение от сервера на консоль
//            System.out.println("Received from server: " + response.getMessage() + " " + response.getError() + " " + response.getOutput());
//        } catch (SocketTimeoutException e) {
//            // Обработка ситуации, когда ответ не был получен вовремя
//            System.out.println("Сервер временно недоступен");
//        }
        SendedCommandResponse response = (SendedCommandResponse) getResponse();


        // Закрываем сокет
        clientSocket.close();

        return response;
    }

    public void createSocket() throws SocketException, UnknownHostException {
        // Создаем сокет для отправки данных
        this.clientSocket = new DatagramSocket();
        // Получаем IP адрес сервера
        this.serverIPAddress = InetAddress.getByName(this.host);
        this.clientSocket.setSoTimeout(15000);
    }

    public void sendPacket(byte[] sendData) throws IOException {
        // Создаем пакет для отправки данных серверу
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, this.port);
        // Отправляем пакет серверу
        this.clientSocket.send(sendPacket);
    }

    public Response getResponse() throws IOException, ClassNotFoundException, SocketTimeoutException {
        // Получение ответа от сервера
        // Создаем буфер для приема данных от сервера
        //todo изменить
        byte[] receiveData = new byte[65507];
        // Создаем пакет для приема данных от сервера
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        // Получаем данные от сервера
        this.clientSocket.receive(receivePacket);
        byte[] data = receivePacket.getData();

        return castToObjectFromByte(data);
    }

    public byte[] castToByte(Serializable data) throws IOException {
        // Преобразуем сообщение в массив байтов
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public Response castToObjectFromByte(byte[] data) throws IOException, ClassNotFoundException {
        // Преобразуем массив байт обратно в объект
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Response) objectInputStream.readObject();
    }
}
