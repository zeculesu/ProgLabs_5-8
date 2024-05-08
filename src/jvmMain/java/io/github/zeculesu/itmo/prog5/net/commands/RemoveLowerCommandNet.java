package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class RemoveLowerCommandNet extends NetCommand<RemoveLowerCommandNet> {
    SpaceMarine spaceMarine;

    public RemoveLowerCommandNet(SpaceMarine spaceMarine){
        this.spaceMarine = spaceMarine;
    }


    private static final class SerializerImpl extends NetObjectSerializer<RemoveLowerCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, RemoveLowerCommandNet value) {
            SpaceMarine.Serializer.serialise(buffer, offset, value.spaceMarine);
        }

        @Override
        public RemoveLowerCommandNet deserialize(byte[] buffer, int offset) {
            return new RemoveLowerCommandNet(SpaceMarine.Serializer.deserialize(buffer, offset));
        }

        @Override
        public int serialisedSize(RemoveLowerCommandNet value) {
            return SpaceMarine.Serializer.serialisedSize(value.spaceMarine);
        }
    }

    public static final NetObjectSerializer<RemoveLowerCommandNet> Serializer = new RemoveLowerCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<RemoveLowerCommandNet> getSerializer() {
        return Serializer;
    }
}
