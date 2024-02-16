package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.manager.CommandIO;

public interface CollectionAction extends Iterable<SpaceMarine> {
    public String info(CommandIO console);
    public String show(CommandIO console);
    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);
    public void update(int id, String name, Coordinates coordinates, int health,
                       AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);
    public String remove_by_id(int id);
    public void clear();
    public void save();

    public String remove_head(CommandIO console);
    public String remove_lower(SpaceMarine o);

    public String remove_all_by_melee_weapon(MeleeWeapon meleeWeapon);
    public String filter_starts_with_name(String name, CommandIO console);
    public String print_field_descending_health();
}
