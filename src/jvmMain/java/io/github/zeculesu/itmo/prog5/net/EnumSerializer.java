package io.github.zeculesu.itmo.prog5.net;

import ru.landgrafhomyak.utility.IntSerializers;

public class EnumSerializer<E extends Enum<E> & NetObject<E>> extends NetObjectSerializer<E> {
    final EnumIdMapper<E> serialIds;

    public EnumSerializer(EnumIdMapper<E> serialIds) {
        super((byte) 0);
        this.serialIds = serialIds;
    }

    @Override
    public void serialise(byte[] buffer, int offset, E value) {
        IntSerializers.encode32beIu(buffer, offset, serialIds.getSerialId(value));
    }

    @Override
    public E deserialize(byte[] buffer, int offset) {
        int serialIdEnumValue = IntSerializers.decode24beIu(buffer, offset);
        return serialIds.resolveBySerialId(serialIdEnumValue);
    }

    @Override
    public int serialisedSize(E value) {
        return 4;
    }
}
