package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class UpdateCommandNet extends NetCommand<UpdateCommandNet> {
    SpaceMarine spaceMarine;

    public UpdateCommandNet(SpaceMarine spaceMarine){
        this.spaceMarine = spaceMarine;
    }


    private static final class SerializerImpl extends NetObjectSerializer<UpdateCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, UpdateCommandNet value) {
            SpaceMarine.Serializer.serialise(buffer, offset, value.spaceMarine);
        }

        @Override
        public UpdateCommandNet deserialize(byte[] buffer, int offset) {
            return new UpdateCommandNet(SpaceMarine.Serializer.deserialize(buffer, offset));
        }

        @Override
        public int serialisedSize(UpdateCommandNet value) {
            return SpaceMarine.Serializer.serialisedSize(value.spaceMarine);
        }
    }

    public static final NetObjectSerializer<UpdateCommandNet> Serializer = new UpdateCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<UpdateCommandNet> getSerializer() {
        return Serializer;
    }
}
