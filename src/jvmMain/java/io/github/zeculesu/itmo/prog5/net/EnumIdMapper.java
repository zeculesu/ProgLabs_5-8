package io.github.zeculesu.itmo.prog5.net;

import java.lang.Integer;

public final class EnumIdMapper<E extends Enum<E> & NetObject<E>> {
    private final E[] id2enum;
    private final int[] enum2id;

    private EnumIdMapper(E[] id2enum, int[] enum2id) {
        this.id2enum = id2enum;
        this.enum2id = enum2id;
    }

    public static <E extends Enum<E> & NetObject<E>> EnumIdMapper.Builder<E> builder(Class<E> clazz) {
        return new Builder<E>(clazz);
    }

    /**
     * @param id serial id og enum value
     * @return enum value or null if id not bound to any value
     */
    public E resolveBySerialId(int id) {
        if (id < 0 || id >= this.id2enum.length) {
            return null;
        }
        return this.id2enum[id];
    }

    public int getSerialId(E enumValue) {
        return this.enum2id[enumValue.ordinal()];
    }

    public static class Builder<E extends Enum<E> & NetObject<E>> {
        private final E[] id2enum;
        private final int[] enum2id;

        /**
         * Array of initialisation state of {@link EnumIdMapper.Builder#enum2id enum2id} to avoid using
         * {@link Integer reference integer type} and reduce memory usage and execution time.
         */
        private final boolean[] enum2id_isSet;

        @SuppressWarnings({"unchecked", "DataFlowIssue"})

        private Builder(Class<E> clazz) {
            int size = clazz.getEnumConstants().length;
            this.id2enum = (E[]) new Object[size];
            this.enum2id = new int[size];
            this.enum2id_isSet = new boolean[size];
        }

        public Builder<E> bind(int id, E value) {
            if (this.enum2id_isSet[value.ordinal()]) {
                throw new IllegalArgumentException(String.format("Duplication of enum entry %s", value));
            }
            if (this.id2enum[id] != null) {
                throw new IllegalArgumentException(String.format("Duplication of id %d", id));
            }
            this.enum2id[value.ordinal()] = id;
            this.id2enum[id] = value;
            this.enum2id_isSet[value.ordinal()] = true;
            return this;
        }

        public EnumIdMapper<E> build() {
            for (boolean value : this.enum2id_isSet) {
                if (!value) {
                    throw new Error("Not all enum entries are bound to serial id");
                }
            }
            for (E value : this.id2enum) {
                if (value == null) {
                    throw new Error("Not all enum entries are bound to serial id");
                }
            }
            return new EnumIdMapper<E>(this.id2enum, this.enum2id);
        }
    }
}
