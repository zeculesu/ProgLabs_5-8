package io.github.zeculesu.itmo.prog5.manager;

import java.io.IOException;

/**
 * Ввод/Вывод команд
 */
public interface CommandIO {
    public void print(String line);
    public void println(String line);
    public String readln() throws IOException;
}
