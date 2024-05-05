package io.github.zeculesu.itmo.prog5.net;

public abstract class NetCommand<T extends NetCommand<T>> {
    final NetCommandSerializer<T> serializer;

    protected NetCommand(NetCommandSerializer<T> serializer) {
        this.serializer = serializer;
    }
}
