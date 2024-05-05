package io.github.zeculesu.itmo.prog5.net;

import ru.landgrafhomyak.utility.IntSerializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

class SerializationUtilities {
    private final static int headerSize = 4;

    <T extends NetObject<T>> byte[] formatPacket(T command) {
        NetObjectSerializer<T> serializer = command.getSerializer();
        int packetSize = headerSize + serializer.serialisedSize(command);
        byte[] buffer = new byte[packetSize];
        IntSerializers.encode24beIu(buffer, 0, packetSize);
        IntSerializers.encode8Bu(buffer, 3, serializer.typeId);
        serializer.serialise(buffer, 4, command);
        return buffer;
    }

    <T extends NetObject<T>> T parsePacket(InputStream stream, Map<Byte, NetObjectSerializer<T>> resolver) throws IOException {
        byte[] header = stream.readNBytes(headerSize);
        int packetSize = IntSerializers.decode24beIu(header, 0);
        byte typeId = IntSerializers.decode8Bu(header, 3);
        NetObjectSerializer<T> serializer = resolver.get(typeId);
        if (serializer == null) {
            return null;
        }
        return serializer.deserialize(stream.readNBytes(packetSize - headerSize), 0);
    }
}
