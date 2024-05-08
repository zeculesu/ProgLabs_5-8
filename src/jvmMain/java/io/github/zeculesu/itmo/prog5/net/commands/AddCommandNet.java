package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.net.NetCommand;

import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class AddCommandNet extends NetCommand<AddCommandNet> {
    SpaceMarine spaceMarine;

    public AddCommandNet(SpaceMarine spaceMarine){
        this.spaceMarine = spaceMarine;
    }


    private static final class SerializerImpl extends NetObjectSerializer<AddCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, AddCommandNet value) {
            SpaceMarine.Serializer.serialise(buffer, offset, value.spaceMarine);
        }

        @Override
        public AddCommandNet deserialize(byte[] buffer, int offset) {
            return new AddCommandNet(SpaceMarine.Serializer.deserialize(buffer, offset));
        }

        @Override
        public int serialisedSize(AddCommandNet value) {
            return SpaceMarine.Serializer.serialisedSize(value.spaceMarine);
        }
    }

    public static final NetObjectSerializer<AddCommandNet> Serializer = new AddCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<AddCommandNet> getSerializer() {
        return Serializer;
    }
}
