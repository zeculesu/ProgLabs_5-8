package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class ShowCommandNet extends NetCommand<ShowCommandNet> {
    private static ShowCommandNet instance;

    private ShowCommandNet() {
    }

    public static ShowCommandNet getInstance() {
        if (instance == null) {
            instance = new ShowCommandNet();
        }
        return instance;
    }

    private static final class SerializerImpl extends NetObjectSerializer<ShowCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, ShowCommandNet value) {
        }

        @Override
        public ShowCommandNet deserialize(byte[] buffer, int offset) {
            return ShowCommandNet.getInstance();
        }

        @Override
        public int serialisedSize(ShowCommandNet value) {
            return 0;
        }
    }

    public static final NetObjectSerializer<ShowCommandNet> Serializer = new ShowCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<ShowCommandNet> getSerializer() {
        return Serializer;
    }
}

