package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.user_interface.ColorConsole;

import java.io.IOException;

/**
 * Ввод/Вывод команд
 */
public interface CommandIO {
    public void print(String line, ColorConsole... color);
    public void println(String line, ColorConsole... color);
    public String readln() throws IOException;
}
