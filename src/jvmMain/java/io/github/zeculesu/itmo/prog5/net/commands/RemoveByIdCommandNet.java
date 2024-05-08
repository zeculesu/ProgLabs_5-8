package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;
import ru.landgrafhomyak.utility.IntSerializers;

public class RemoveByIdCommandNet extends NetCommand<RemoveByIdCommandNet> {

    private int id;

    public RemoveByIdCommandNet(int id) {
        this.id = id;
    }

    private static final class SerializerImpl extends NetObjectSerializer<RemoveByIdCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, RemoveByIdCommandNet value) {
            IntSerializers.encode32beIu(buffer, offset, value.id);
        }

        @Override
        public RemoveByIdCommandNet deserialize(byte[] buffer, int offset) {
            return new RemoveByIdCommandNet(IntSerializers.decode32beIu(buffer, offset));
        }

        @Override
        public int serialisedSize(RemoveByIdCommandNet value) {
            return 4;
        }
    }

    public static final NetObjectSerializer<RemoveByIdCommandNet> Serializer = new RemoveByIdCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<RemoveByIdCommandNet> getSerializer() {
        return Serializer;
    }
}


