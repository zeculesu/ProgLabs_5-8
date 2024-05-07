package io.github.zeculesu.itmo.prog5.net;

public abstract class NetObjectSerializer<T extends NetObject<T>> {
    byte typeId;

    public NetObjectSerializer(byte typeId) {
        this.typeId = typeId;
    }

    public abstract void serialise(byte[] buffer, int offset, T value);
    public abstract T deserialize(byte[] buffer, int offset);
    public abstract int serialisedSize(T value);
}
