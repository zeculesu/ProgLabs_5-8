package io.github.zeculesu.itmo.prog5.net;

public abstract class NetCommandSerializer<T extends NetCommand<T>> {
    byte typeId;

    public NetCommandSerializer(byte typeId) {
        this.typeId = typeId;
    }

    abstract void serialise(byte[] buffer, int offset, T value);
    abstract T deserialise(byte[] buffer, int offset);
    abstract int serialisedSize(T value);
}
