package io.github.zeculesu.itmo.prog5.models;


import io.github.zeculesu.itmo.prog5.net.NetObject;
import io.github.zeculesu.itmo.prog5.net.NetObjectSerializer;
import io.github.zeculesu.itmo.prog5.net.SerialiseStringUtilities;
import org.jetbrains.annotations.NotNull;
import ru.landgrafhomyak.utility.IntSerializers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Класс элементов, с которыми работаем в программе
 */
public class SpaceMarine implements Comparable<SpaceMarine>, NetObject<SpaceMarine> {
    private final int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int health; //Поле не может быть null, Значение поля должно быть больше 0
    private AstartesCategory category; //Поле может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public SpaceMarine(int id, String name, Coordinates coordinates, int health,
                       AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this(id, name, coordinates, new Date(), health, category, weaponType, meleeWeapon, chapter);
    }

    public SpaceMarine(int id, String name, Coordinates coordinates, Date creationDate, int health,
                       AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:ss");
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates={x = " + coordinates.getX() +
                "; y = " + coordinates.getY() + "}" +
                ", creationDate=\"" + df.format(creationDate) + "\"" +
                ", health=" + health +
                ", category=" + category +
                ", weaponType=" + weaponType +
                ", meleeWeapon=" + meleeWeapon +
                ", chapter={name=" + chapter.getName() +
                "; parent_legion=" + chapter.getParentLegion() + "}" +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public int compareTo(@NotNull SpaceMarine o) {
        return this.getName().compareTo(o.getName());
    }

    private static class SerializerImpl extends NetObjectSerializer<SpaceMarine> {
        public SerializerImpl() {
            super((byte) 0);
        }

        @Override
        public void serialise(byte[] buffer, int offset, SpaceMarine value) {
            IntSerializers.encode32beIu(buffer, offset, value.getId());
            offset += 4;

            offset = SerialiseStringUtilities.serialise(buffer, offset, value.getName());

            Coordinates.Serializer.serialise(buffer, offset, value.getCoordinates());
            offset = Coordinates.Serializer.serialisedSize(value.getCoordinates());

            IntSerializers.encode64beLu(buffer, offset, value.getCreationDate().toInstant().getEpochSecond());

            IntSerializers.encode32beIu(buffer, offset + 8, value.getHealth());
            offset += 4;

            AstartesCategory.Serializer.serialise(buffer, offset, value.getCategory());
            offset = AstartesCategory.Serializer.serialisedSize(value.getCategory());

            Weapon.Serializer.serialise(buffer, offset, value.getWeaponType());
            offset = Weapon.Serializer.serialisedSize(value.getWeaponType());

            MeleeWeapon.Serializer.serialise(buffer, offset, value.getMeleeWeapon());
            offset = MeleeWeapon.Serializer.serialisedSize(value.getMeleeWeapon());

            Chapter.Serializer.serialise(buffer, offset, value.getChapter());
        }

        @Override
        public SpaceMarine deserialize(byte[] buffer, int offset) {
            int id = IntSerializers.decode32beIu(buffer, offset);
            offset += 4;

            String name = SerialiseStringUtilities.deserialize(buffer, offset);
            offset += SerialiseStringUtilities.getDeserializationOffset(buffer, offset);

            Coordinates coordinates = Coordinates.Serializer.deserialize(buffer, offset);
            offset += Coordinates.Serializer.serialisedSize(coordinates);

            long data = IntSerializers.decode64beL(buffer, offset);
            Date creationData = Date.from(Instant.ofEpochSecond(data));

            int health = IntSerializers.decode32beIu(buffer, offset + 8);
            offset += 4;

            AstartesCategory astartesCategory = AstartesCategory.Serializer.deserialize(buffer, offset);
            offset += AstartesCategory.Serializer.serialisedSize(astartesCategory);

            Weapon weapon = Weapon.Serializer.deserialize(buffer, offset);
            offset += Weapon.Serializer.serialisedSize(weapon);

            MeleeWeapon meleeWeapon = MeleeWeapon.Serializer.deserialize(buffer, offset);
            offset += MeleeWeapon.Serializer.serialisedSize(meleeWeapon);

            Chapter chapter = Chapter.Serializer.deserialize(buffer, offset);

            return new SpaceMarine(id, name, coordinates, creationData, health, astartesCategory,
                    weapon, meleeWeapon, chapter);
        }

        @Override
        public int serialisedSize(SpaceMarine value) {
            return 4 +
                    SerialiseStringUtilities.serialisedSize(value.getName()) +
                    Coordinates.Serializer.serialisedSize(value.getCoordinates()) +
                    8 +
                    4 +
                    AstartesCategory.Serializer.serialisedSize(value.getCategory()) +
                    Weapon.Serializer.serialisedSize(value.getWeaponType()) +
                    MeleeWeapon.Serializer.serialisedSize(value.getMeleeWeapon()) +
                    Chapter.Serializer.serialisedSize(value.getChapter());
        }
    }

    public static final NetObjectSerializer<SpaceMarine> Serializer = new SpaceMarine.SerializerImpl();

    @Override
    public NetObjectSerializer<SpaceMarine> getSerializer() {
        return Serializer;
    }
}
