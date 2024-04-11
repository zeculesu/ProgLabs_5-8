package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.data.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ResponseSending {
    public static void responseSend(DatagramSocket serverSocket, DatagramPacket receivePacket, Response response) throws IOException {
        // �������� ������ ������� (� ������ ������ ������ ���)
        // �������� IP ����� � ���� �������, ����� ��������� ����� �� ���� �� ������
        InetAddress clientIPAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        // ������� ��������� ��� �������� �������
        String responseMessage = "Hello, client!";
        // ����������� ��������� � ������ ������
        byte[] sendData = responseMessage.getBytes();
        // ������� ����� ��� �������� ������ �������
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
        // ���������� ����� �������
        serverSocket.send(sendPacket);
    }
}
