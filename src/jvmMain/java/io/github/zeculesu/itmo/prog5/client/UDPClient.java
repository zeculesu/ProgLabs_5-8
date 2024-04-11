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
                // ������� ����� ��� �������� ������
                DatagramSocket clientSocket = new DatagramSocket();
                // �������� IP ����� �������
                InetAddress serverIPAddress = InetAddress.getByName("localhost");

                // �������� ��������� �������
                // ������� ��������� ��� ��������
                String message = scanner.nextLine();
                // ����������� ��������� � ������ ������
                byte[] sendData = message.getBytes();
                // ������� ����� ��� �������� ������ �������
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, 9876);
                // ���������� ����� �������
                clientSocket.send(sendPacket);

                // ��������� ������ �� �������
                // ������� ����� ��� ������ ������ �� �������
                byte[] receiveData = new byte[1024];
                // ������� ����� ��� ������ ������ �� �������
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // �������� ������ �� �������
                clientSocket.receive(receivePacket);
                // ����������� ������ � ������ ����
                byte[] data = receivePacket.getData();
                // ����������� ������ ���� ������� � ������
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Response response = (Response) objectInputStream.readObject();

                // ������� ���������� ��������� �� ������� �� �������
                System.out.println("Received from server: " + response.getMessage() + " " + response.getError()  + " " + response.getOutput());

                // ��������� �����
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
