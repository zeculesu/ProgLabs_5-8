package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class PrintFieldHealthCommandNet extends NetCommand<PrintFieldHealthCommandNet> {
    private static PrintFieldHealthCommandNet instance;

    private PrintFieldHealthCommandNet(){}

    public static PrintFieldHealthCommandNet getInstance(){ // #3
        if(instance == null){
            instance = new PrintFieldHealthCommandNet();
        }
        return instance;
    }

    private static final class SerializerImpl extends NetObjectSerializer<PrintFieldHealthCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, PrintFieldHealthCommandNet value) {
        }

        @Override
        public PrintFieldHealthCommandNet deserialize(byte[] buffer, int offset) {
            return PrintFieldHealthCommandNet.getInstance();
        }

        @Override
        public int serialisedSize(PrintFieldHealthCommandNet value) {
            return 0;
        }
    }

    public static final NetObjectSerializer<PrintFieldHealthCommandNet> Serializer = new PrintFieldHealthCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<PrintFieldHealthCommandNet> getSerializer() {
        return Serializer;
    }
}
