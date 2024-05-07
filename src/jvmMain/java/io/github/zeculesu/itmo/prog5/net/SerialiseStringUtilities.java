package io.github.zeculesu.itmo.prog5.net;

import kotlin.text.StringsKt;
import ru.landgrafhomyak.utility.IntSerializers;

public class SerialiseStringUtilities {
    /**
     * serialise String
     *
     * @return new offset
     */
    public static int serialise(byte[] buffer, int offset, String value) {
        byte[] valueBytes = StringsKt.encodeToByteArray(value);
        int sizeValueBytes = valueBytes.length;
        IntSerializers.encode32beIu(buffer, offset, sizeValueBytes);
        System.arraycopy(valueBytes, 0, buffer, offset + 4, sizeValueBytes);
        return offset + 4 + sizeValueBytes;
    }

    public static int getDeserializationOffset(byte[] buffer, int offset) {
        int sizeValue = IntSerializers.decode32beIu(buffer, offset);
        return offset + 4 + sizeValue;
    }

    public static String deserialize(byte[] buffer, int offset) {
        int sizeValue = IntSerializers.decode32beIu(buffer, offset);
        return StringsKt.decodeToString(buffer, offset + 4, offset + 4 + sizeValue);
    }

    public static int serialisedSize(String value){
        return StringsKt.encodeToByteArray(value).length;
    }
}
