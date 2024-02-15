package io.github.zeculesu.itmo.prog5.data;

public interface CollectionAction extends Iterable<SpaceMarine> {
    public String info();
    public String show();
    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);
    public void update(int id, String name, Coordinates coordinates, int health,
                       AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter);
    public void remove_by_id(int id);
    public void clear();
    public void save();

    public String remove_head();
    public void remove_lower(SpaceMarine o);

    public void remove_all_by_melee_weapon(MeleeWeapon meleeWeapon);
    public String filter_starts_with_name(String name);
    public String print_field_descending_health();
}
