package io.github.zeculesu.itmo.prog5.server;

import io.github.zeculesu.itmo.prog5.client.UDPClient;
import io.github.zeculesu.itmo.prog5.models.Request;
import io.github.zeculesu.itmo.prog5.models.Response;

import java.io.IOException;

public class Auth {

    public static Response sendAuth(String login, String password, UDPClient udpClient) throws IOException, ClassNotFoundException {
        udpClient.createSocket();

        // Отправка сообщения серверу
        Request request = new Request();
        request.setCommand("auth");
        request.setArg(login + " " + password);

        byte[] sendData = UDPClient.castToByte(request);

        udpClient.sendPacket(sendData);
        Response response = udpClient.getResponse();
        // Закрываем сокет
        udpClient.closeClientSocket();

        return response;
    }

    public static Response checkUniqLogin(String login, UDPClient udpClient) throws IOException, ClassNotFoundException {
        udpClient.createSocket();

        // Отправка сообщения серверу
        Request request = new Request();
        request.setCommand("check_uniq_login");
        request.setArg(login);

        byte[] sendData = UDPClient.castToByte(request);

        udpClient.sendPacket(sendData);
        Response response = udpClient.getResponse();
        // Закрываем сокет
        udpClient.closeClientSocket();

        return response;
    }

    public static Response sendReg(String login, String password, UDPClient udpClient) throws IOException, ClassNotFoundException {
        udpClient.createSocket();

        // Отправка сообщения серверу
        Request request = new Request();
        request.setCommand("register");
        request.setArg(login + " " + password);

        byte[] sendData = UDPClient.castToByte(request);

        udpClient.sendPacket(sendData);
        Response response = udpClient.getResponse();
        // Закрываем сокет
        udpClient.closeClientSocket();

        return response;
    }
}
