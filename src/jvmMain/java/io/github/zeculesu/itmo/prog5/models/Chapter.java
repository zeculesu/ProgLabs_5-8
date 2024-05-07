package io.github.zeculesu.itmo.prog5.models;

import io.github.zeculesu.itmo.prog5.net.NetObject;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;
import io.github.zeculesu.itmo.prog5.net.SerialiseStringUtilities;

/**
 * Класс описывающий главу для SpaceMarine
 */
public class Chapter implements NetObject<Chapter> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;

    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    public String getName() {
        return name;
    }

    public String getParentLegion() {
        return parentLegion;
    }

    private static final class SerializerImpl extends NetObjectSerializer<Chapter> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, Chapter value) {
            offset = SerialiseStringUtilities.serialise(buffer, offset, value.getName());
            SerialiseStringUtilities.serialise(buffer, offset, value.getParentLegion());
        }

        @Override
        public Chapter deserialize(byte[] buffer, int offset) {
            String name = SerialiseStringUtilities.deserialize(buffer, offset);
            offset = SerialiseStringUtilities.getDeserializationOffset(buffer, offset);
            String parentLegion = SerialiseStringUtilities.deserialize(buffer, offset);
            return new Chapter(name, parentLegion);
        }

        @Override
        public int serialisedSize(Chapter value) {
            return SerialiseStringUtilities.serialisedSize(value.getName())
                    + SerialiseStringUtilities.serialisedSize(value.getParentLegion());
        }
    }

    public static final NetObjectSerializer<Chapter> Serializer = new Chapter.SerializerImpl();

    @Override
    public NetObjectSerializer<Chapter> getSerializer() {
        return Serializer;
    }
}
