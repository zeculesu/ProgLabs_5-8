package io.github.zeculesu.itmo.prog5.manager;

import java.util.Iterator;

/**
 * Ввод команд из итерируемого объекта
 */
public class CommandIOMemoryImpl implements CommandIO{

    final Iterator<String> input;
    final StringBuilder output = new StringBuilder();

    public CommandIOMemoryImpl(Iterable<String> input){
        this.input = input.iterator();
    }

    @Override
    public void print(String line) {
        this.output.append(line);

    }

    @Override
    public void println(String line) {
        this.output.append(line);
        this.output.append('\n');
    }

    @Override
    public String readln() {
        return this.input.hasNext() ? this.input.next() : null;
    }
}
