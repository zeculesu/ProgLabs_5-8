package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String[] arg;
    private SpaceMarine elem;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getArg() {
        return arg;
    }

    public void setArg(String[] arg) {
        this.arg = arg;
    }

    public SpaceMarine getElem() {
        return elem;
    }

    public void setElem(SpaceMarine elem) {
        this.elem = elem;
    }
}
