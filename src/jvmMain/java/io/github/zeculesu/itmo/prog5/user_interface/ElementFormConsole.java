package io.github.zeculesu.itmo.prog5.user_interface;

import io.github.zeculesu.itmo.prog5.data.*;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static kotlin.io.ConsoleKt.readlnOrNull;

public class ElementFormConsole {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int health; //Поле не может быть null, Значение поля должно быть больше 0
    private AstartesCategory category; //Поле может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public ElementFormConsole(CommandIO console) throws InputFormException, NamingEnumException, NullPointerException {
        String input;
        console.print("Введите имя: ");
        //todo проверка на null
        input = console.readln();
        this.name = check_name(input);

        console.print("Введите координату (x и y через пробел): ");
        input = console.readln();
        this.coordinates = check_coordinates(input);

        console.print("Введите количество здоровья: ");
        input = console.readln();
        this.health = check_health(input);

        console.print("Введите категорию (SCOUT, SUPPRESSOR, LIBRARIAN, HELIX): ");
        input = console.readln();
        this.category = check_category(input);

        console.print("Введите тип оружия (BOLTGUN, HEAVY_BOLTGUN, BOLT_RIFLE, FLAMER, MULTI_MELTA): ");
        input = console.readln();
        this.weaponType = check_weaponType(input);

        console.print("Введите оружие ближнего боя (CHAIN_SWORD, POWER_SWORD, CHAIN_AXE, MANREAPER, POWER_BLADE): ");
        input = console.readln();
        this.meleeWeapon = check_meleeWeapon(input);

        console.print("Введите орден (имя и родительским легион через пробел): ");
        input = console.readln();
        this.chapter = check_chapter(input);
    }

    public static int check_id(String input) throws InputFormException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод id");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputFormException("id должно быть числом");
        }
    }

    public static String check_name(String input) throws InputFormException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод имени");
        return input;
    }

    public static Coordinates check_coordinates(String input) throws InputFormException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод координат");
        String[] coord = input.split(" ");
        if (coord.length != 2) throw new InputFormException("Неправильный ввод координат");

        try {
            // todo проверка на -67
            Long x = Long.parseLong(coord[0]);
            Float y = Float.parseFloat(coord[1]);
            if (y < -67) throw new InputFormException("y должен быть больше -67");
            return new Coordinates(x, y);
        } catch (NumberFormatException e) {
            throw new InputFormException("Неправильный ввод для координат, ожидается x-long, y-float");
        }
    }

    public static Date check_creationDate(String input) throws InputFormException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод даты");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:ss");
            return formatter.parse(input);
        } catch (ParseException e) {
            throw new InputFormException("Невалидный ввод даты");
        }
    }

    public static int check_health(String input) throws InputFormException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод количества здоровья");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputFormException("количество здоровья должно быть числом");
        }
    }

    public static AstartesCategory check_category(String input) throws InputFormException, NamingEnumException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод категории");
        return AstartesCategory.getCategoryByName(input);
    }

    //todo переделать названия на нормальные check_category -> checkCategory

    public static Weapon check_weaponType(String input) throws InputFormException, NamingEnumException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод оружия");
        return Weapon.getWeaponByName(input);
    }

    public static MeleeWeapon check_meleeWeapon(String input) throws InputFormException, NamingEnumException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод оружия ближнего боя");
        return MeleeWeapon.getMeleeWeaponByName(input);
    }

    public static Chapter check_chapter(String input) throws InputFormException {
        if (input.isEmpty()) throw new InputFormException("Неправильный ввод ордена");
        String[] ch = input.split(" ");
        if (ch.length != 2) throw new InputFormException("Неправильный ввод ордена");

        try {
            return new Chapter(ch[0], ch[1]);
        } catch (Exception e) {
            throw new InputFormException("Неправильный ввод ордена");
        }
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public int getHealth() {
        return this.health;
    }

    public AstartesCategory getCategory() {
        return this.category;
    }

    public Weapon getWeaponType() {
        return this.weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return this.meleeWeapon;
    }

    public Chapter getChapter() {
        return this.chapter;
    }
}

