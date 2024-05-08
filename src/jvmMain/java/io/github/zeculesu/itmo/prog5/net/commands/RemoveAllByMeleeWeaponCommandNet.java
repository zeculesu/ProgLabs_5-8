package io.github.zeculesu.itmo.prog5.net.commands;

import io.github.zeculesu.itmo.prog5.models.MeleeWeapon;
import io.github.zeculesu.itmo.prog5.net.NetCommand;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;

public class RemoveAllByMeleeWeaponCommandNet extends NetCommand<RemoveAllByMeleeWeaponCommandNet> {
    private MeleeWeapon meleeWeapon;

    public RemoveAllByMeleeWeaponCommandNet(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    private static final class SerializerImpl extends NetObjectSerializer<RemoveAllByMeleeWeaponCommandNet> {

        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, RemoveAllByMeleeWeaponCommandNet value) {
            MeleeWeapon.Serializer.serialise(buffer, offset, value.meleeWeapon);
        }

        @Override
        public RemoveAllByMeleeWeaponCommandNet deserialize(byte[] buffer, int offset) {
            return new RemoveAllByMeleeWeaponCommandNet(MeleeWeapon.Serializer.deserialize(buffer, offset));
        }

        @Override
        public int serialisedSize(RemoveAllByMeleeWeaponCommandNet value) {
            return MeleeWeapon.Serializer.serialisedSize(value.meleeWeapon);
        }
    }

    public static final NetObjectSerializer<RemoveAllByMeleeWeaponCommandNet> Serializer = new RemoveAllByMeleeWeaponCommandNet.SerializerImpl();

    @Override
    public NetObjectSerializer<RemoveAllByMeleeWeaponCommandNet> getSerializer() {
        return Serializer;
    }
}
