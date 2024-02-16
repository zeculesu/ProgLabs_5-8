package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.*;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;


public class AddCommand implements CommandAction{

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO cns, ConsoleCommandEnvironment env, String[] args) {
        cns.print("Введите имя: ");
        //todo проверка на null
        String name = cns.readln();

        cns.print("Введите координату: ");
        String[] coord = cns.readln().split(" ");
        // todo проверка на -67
        Coordinates coordinates = new Coordinates(Long.parseLong(coord[0]), Float.parseFloat(coord[1]));

        cns.print("Введите количество здоровья");
        int health = Integer.parseInt(cns.readln());

        cns.print("Введите категорию (SCOUT, SUPPRESSOR, LIBRARIAN, HELIX): ");
        AstartesCategory category = AstartesCategory.getCategoryByName(cns.readln());

        cns.print("Введите тип оружия (BOLTGUN, HEAVY_BOLTGUN, BOLT_RIFLE, FLAMER, MULTI_MELTA): ");
        Weapon weapon = Weapon.getWeaponByName(cns.readln());

        cns.print("Введите оружие ближнего боя (CHAIN_SWORD, POWER_SWORD, CHAIN_AXE, MANREAPER, POWER_BLADE): ");
        MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeaponByName(cns.readln());

        cns.print("Введите орден (имя и родительским легион через пробел): ");
        String[] ch = cns.readln().split(" ");
        Chapter chapter = new Chapter(ch[0], ch[1]);

        collectionSpaceMarine.add(name, coordinates, health, category, weapon, meleeWeapon, chapter);

        return "Новый элемент добавлен";
    }

    @NotNull
    @Override
    public String getName() {
        return "add";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
