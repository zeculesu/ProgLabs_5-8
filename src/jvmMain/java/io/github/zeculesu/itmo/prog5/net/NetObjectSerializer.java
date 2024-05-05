package io.github.zeculesu.itmo.prog5.net;

public abstract class NetObjectSerializer<T extends NetObject<T>> {
    byte typeId;

    public NetObjectSerializer(byte typeId) {
        this.typeId = typeId;
    }

    abstract void serialise(byte[] buffer, int offset, T value);
    abstract T deserialize(byte[] buffer, int offset);
    abstract int serialisedSize(T value);
}
