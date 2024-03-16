package io.github.zeculesu.itmo.prog5.user_interface;

public enum ColorConsole {
    ERROR("\u001B[31m"),
    DEFAULT("\u001B[0m");

    private final String ansiCode;
    ColorConsole(String s) {
        this.ansiCode = s;
    }

    public String getAnsiCode() {
        return ansiCode;
    }
}
