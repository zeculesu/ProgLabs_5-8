package io.github.zeculesu.itmo.prog5;

import io.github.zeculesu.itmo.prog5.client.CLientConsole;


public class MainClient {
    public static void main(String[] args) {
        CLientConsole console = new CLientConsole("helios.se.ifmo", 45000);
        console.start();
    }
}
