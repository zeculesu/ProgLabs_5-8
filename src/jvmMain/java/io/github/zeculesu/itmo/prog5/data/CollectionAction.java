package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.Console;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;

import java.util.ArrayList;
import java.util.Date;

public interface CollectionAction extends Iterable<SpaceMarine> {
    public ArrayList<String> info();

    public ArrayList<SpaceMarine> show();

    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);

    public void addFromFile(int id, String name, Coordinates coordinates, Date creationDate, int health,
                            AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);

    public void update(int id, String name, Coordinates coordinates, int health,
                         AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);

    public void remove_by_id(int id);

    public void clear();

    public void save(String filename);

    public SpaceMarine remove_head();

    public void remove_lower(SpaceMarine o);

    public void remove_all_by_melee_weapon(MeleeWeapon meleeWeapon);

    public ArrayList<SpaceMarine> filter_starts_with_name(String name);

    public String print_field_descending_health();

    public int size();

    public SpaceMarine findById(int id);
}


