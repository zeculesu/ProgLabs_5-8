package io.github.zeculesu.itmo.prog5.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Действия, реализуемые с коллекцией
 */
public interface CollectionAction extends Iterable<SpaceMarine> {
    public ArrayList<String> info();

    public ArrayList<SpaceMarine> show();

    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);

    public void addFromFile(int id, String name, Coordinates coordinates, Date creationDate, int health,
                            AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);

    public void update(int id, String name, Coordinates coordinates, int health,
                         AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);

    public void removeById(int id);

    public void clear();

    public void save(String filename);

    public SpaceMarine removeHead();

    public void removeLower(SpaceMarine o);

    public void removeAllByMeleeWeapon(MeleeWeapon meleeWeapon);

    public ArrayList<SpaceMarine> filterStartsWithName(String name);

    public String printFieldDescendingHealth();

    public int size();

    public SpaceMarine findById(int id);
}


