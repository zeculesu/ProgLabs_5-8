package io.github.zeculesu.itmo.prog5.client;

import io.github.zeculesu.itmo.prog5.data.Response;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                // Создаем сокет для отправки данных
                DatagramSocket clientSocket = new DatagramSocket();
                // Получаем IP адрес сервера
                InetAddress serverIPAddress = InetAddress.getByName("localhost");

                // Отправка сообщения серверу
                // Создаем сообщение для отправки
                String message = scanner.nextLine();
                // Преобразуем сообщение в массив байтов
                byte[] sendData = message.getBytes();
                // Создаем пакет для отправки данных серверу
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, 9876);
                // Отправляем пакет серверу
                clientSocket.send(sendPacket);

                // Получение ответа от сервера
                // Создаем буфер для приема данных от сервера
                byte[] receiveData = new byte[1024];
                // Создаем пакет для приема данных от сервера
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Получаем данные от сервера
                clientSocket.receive(receivePacket);
                // Преобразуем данные в массив байт
                byte[] data = receivePacket.getData();
                // Преобразуем массив байт обратно в объект
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Response response = (Response) objectInputStream.readObject();

                // Выводим полученное сообщение от сервера на консоль
                System.out.println("Received from server: " + response.getMessage() + " " + response.getError()  + " " + response.getOutput());

                // Закрываем сокет
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
