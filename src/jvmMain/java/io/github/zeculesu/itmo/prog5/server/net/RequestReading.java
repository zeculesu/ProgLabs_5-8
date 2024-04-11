package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.server.command.HelpCommand;

import java.net.DatagramPacket;

public class RequestReading {
    public static Response requestRead(DatagramPacket receivePacket){
        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
        // ������� ���������� ��������� �� ������� �� �������
        System.out.println("Received from client: " + message);

        return new Response();
    }
}
