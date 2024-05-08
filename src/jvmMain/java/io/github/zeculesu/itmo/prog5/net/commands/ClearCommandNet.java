package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class ClearCommandNet extends NetCommand<ClearCommandNet> {
    private static ClearCommandNet instance;

    private ClearCommandNet(){}

    public static ClearCommandNet getInstance(){ // #3
        if(instance == null){
            instance = new ClearCommandNet();
        }
        return instance;
    }

    private static final class SerializerImpl extends NetObjectSerializer<ClearCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, ClearCommandNet value) {
        }

        @Override
        public ClearCommandNet deserialize(byte[] buffer, int offset) {
            return ClearCommandNet.getInstance();
        }

        @Override
        public int serialisedSize(ClearCommandNet value) {
            return 0;
        }
    }

    public static final NetObjectSerializer<ClearCommandNet> Serializer = new ClearCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<ClearCommandNet> getSerializer() {
        return Serializer;
    }
}

