package io.github.zeculesu.itmo.prog5.net;

import ru.landgrafhomyak.utility.IntSerializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

class SerializationUtilities {
    <T extends NetCommand<T>> byte[] formatCommandPacket(T command) {
        NetCommandSerializer<T> serializer = command.serializer;
        int packetSize = serializer.serialisedSize(command) + 4;
        byte[] buffer = new byte[packetSize];
        IntSerializers.encode24beIu(buffer, 0, packetSize);
        IntSerializers.encode8Bu(buffer, 3, serializer.typeId);
        serializer.serialise(buffer, 4, command);
        return buffer;
    }

    NetCommand<?> parseCommandPacket(InputStream stream, Map<Byte, NetCommandSerializer<?>> resolver) throws IOException {
        byte[] header = stream.readNBytes(4);
        int packetSize = IntSerializers.decode24beIu(header, 0);
        byte typeId = IntSerializers.decode8Bu(header, 3);
        NetCommandSerializer<?> serializer = resolver.get(typeId);
        if (serializer == null) {
            return null;
        }
        return serializer.deserialise(stream.readNBytes(packetSize - 4), 0);
    }
}
