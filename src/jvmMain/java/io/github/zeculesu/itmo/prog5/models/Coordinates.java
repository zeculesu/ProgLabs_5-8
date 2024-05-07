package io.github.zeculesu.itmo.prog5.models;

import io.github.zeculesu.itmo.prog5.net.NetObject;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;
import ru.landgrafhomyak.utility.IntSerializers;

/**
 * Координаты где находится SpaceMarine
 */
@SuppressWarnings("ClassCanBeRecord")
public class Coordinates implements NetObject<Coordinates> {
    private final Long x; //Поле не может быть null
    private final float y; //Значение поля должно быть больше -67

    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private static final class SerializerImpl extends NetObjectSerializer<Coordinates> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, Coordinates value) {
            IntSerializers.encode64beL(buffer, offset, value.getX());
            IntSerializers.encode32beIu(buffer, offset + 8, Float.floatToIntBits(value.getY()));
        }

        @Override
        public Coordinates deserialize(byte[] buffer, int offset) {
            return new Coordinates(
                    IntSerializers.decode64beL(buffer, offset), IntSerializers.decode32beIu(buffer, offset + 8));
        }

        @Override
        public int serialisedSize(Coordinates value) {
            return 12;
        }
    }

    public static final NetObjectSerializer<Coordinates> Serializer = new Coordinates.SerializerImpl();

    @Override
    public NetObjectSerializer<Coordinates> getSerializer() {
        return Serializer;
    }
}