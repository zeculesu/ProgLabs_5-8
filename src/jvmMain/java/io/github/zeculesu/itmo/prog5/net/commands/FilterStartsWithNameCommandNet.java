package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;
import io.github.zeculesu.itmo.prog5.net.SerialiseStringUtilities;

public class FilterStartsWithNameCommandNet extends NetCommand<FilterStartsWithNameCommandNet> {
    private String name;

    public FilterStartsWithNameCommandNet(String name) {
        this.name = name;
    }

    private static final class SerializerImpl extends NetObjectSerializer<FilterStartsWithNameCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, FilterStartsWithNameCommandNet value) {
            SerialiseStringUtilities.serialise(buffer, offset, value.name);
        }

        @Override
        public FilterStartsWithNameCommandNet deserialize(byte[] buffer, int offset) {
            return new FilterStartsWithNameCommandNet(SerialiseStringUtilities.deserialize(buffer, offset));
        }

        @Override
        public int serialisedSize(FilterStartsWithNameCommandNet value) {
            return SerialiseStringUtilities.serialisedSize(value.name);
        }
    }

    public static final NetObjectSerializer<FilterStartsWithNameCommandNet> Serializer = new FilterStartsWithNameCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<FilterStartsWithNameCommandNet> getSerializer() {
        return Serializer;
    }
}
