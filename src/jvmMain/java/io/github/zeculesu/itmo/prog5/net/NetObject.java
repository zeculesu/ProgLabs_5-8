package io.github.zeculesu.itmo.prog5.net;

public interface NetObject<T extends NetObject<T>> {
    NetObjectSerializer<T> getSerializer();
}
