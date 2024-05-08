package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class RemoveHeadCommandNet extends NetCommand<RemoveHeadCommandNet> {
    private static RemoveHeadCommandNet instance;

    private RemoveHeadCommandNet() {
    }

    public static RemoveHeadCommandNet getInstance() {
        if (instance == null) {
            instance = new RemoveHeadCommandNet();
        }
        return instance;
    }

    private static final class SerializerImpl extends NetObjectSerializer<RemoveHeadCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, RemoveHeadCommandNet value) {
        }

        @Override
        public RemoveHeadCommandNet deserialize(byte[] buffer, int offset) {
            return RemoveHeadCommandNet.getInstance();
        }

        @Override
        public int serialisedSize(RemoveHeadCommandNet value) {
            return 0;
        }
    }

    public static final NetObjectSerializer<RemoveHeadCommandNet> Serializer = new RemoveHeadCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<RemoveHeadCommandNet> getSerializer() {
        return Serializer;
    }
}

