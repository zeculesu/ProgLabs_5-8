package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class InfoCommandNet extends NetCommand<InfoCommandNet> {
    private static InfoCommandNet instance;

    private InfoCommandNet(){}

    public static InfoCommandNet getInstance(){ // #3
        if(instance == null){
            instance = new InfoCommandNet();
        }
        return instance;
    }

    private static final class SerializerImpl extends NetObjectSerializer<InfoCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, InfoCommandNet value) {
        }

        @Override
        public InfoCommandNet deserialize(byte[] buffer, int offset) {
            return InfoCommandNet.getInstance();
        }

        @Override
        public int serialisedSize(InfoCommandNet value) {
            return 0;
        }
    }

    public static final NetObjectSerializer<InfoCommandNet> Serializer = new InfoCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<InfoCommandNet> getSerializer() {
        return Serializer;
    }
}
