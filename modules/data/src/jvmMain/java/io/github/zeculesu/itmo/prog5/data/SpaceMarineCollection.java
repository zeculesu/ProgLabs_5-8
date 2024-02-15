package io.github.zeculesu.itmo.prog5.data;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SpaceMarineCollection implements CollectionAction {
    private final PriorityQueue<SpaceMarine> collectionSpaceMarine = new PriorityQueue<>();
    Date dateInitialization;

    public SpaceMarineCollection() {
        this.dateInitialization = new Date();
    }

    @Override
    public String info() {
        return "Тип коллекции: " + this.collectionSpaceMarine.getClass().getName() + "\n" +
                "Дата инициализации: " + this.dateInitialization.toString() + "\n" +
                "Количество элементов: " + this.collectionSpaceMarine.toArray().length + "\n";
    }

    @Override
    public String show() {
        StringBuilder elems_info = new StringBuilder();
        for (SpaceMarine elem : this.collectionSpaceMarine){
            elems_info.append(elem.toString()).append('\n');
        }
        return elems_info.toString();
    }

    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        //todo изменить способ выдачи id
        int id = !collectionSpaceMarine.isEmpty() ? collectionSpaceMarine.size() + 1 : 1;
        SpaceMarine newElement = new SpaceMarine(id, name, coordinates, health, category, weaponType, meleeWeapon, chapter);
        this.collectionSpaceMarine.add(newElement);
    }

    @Override
    public void update(int id, String name, Coordinates coordinates, int health,
                       AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getId() == id){
                elem.setName(name);
                elem.setCoordinates(coordinates);
                elem.setHealth(health);
                elem.setCategory(category);
                elem.setWeaponType(weaponType);
                elem.setMeleeWeapon(meleeWeapon);
                elem.setChapter(chapter);
                break;
            }
        }
    }

    @Override
    public void remove_by_id(int id) {
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getId() == id){
                this.collectionSpaceMarine.remove(elem);
                break;
            }
        }
    }

    @Override
    public void clear() {
        this.collectionSpaceMarine.clear();
    }

    @Override
    public void save() {
        //todo сделать
    }

    @Override
    public String remove_head() {
        assert collectionSpaceMarine.peek() != null; //todo проверить что не null
        SpaceMarine head = this.collectionSpaceMarine.peek();
        this.collectionSpaceMarine.remove(head);
        return head.toString();
    }

    @Override
    public void remove_lower(SpaceMarine o) {
        this.collectionSpaceMarine.removeIf(n -> (n.compareTo(o) < 0));
    }

    @Override
    public void remove_all_by_melee_weapon(MeleeWeapon meleeWeapon) {
        this.collectionSpaceMarine.removeIf(n -> (n.getMeleeWeapon() == meleeWeapon));
    }

    @Override
    public String filter_starts_with_name(String name) {
        StringBuilder elems_info = new StringBuilder();
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getName().startsWith(name)){
                elems_info.append(elem).append('\n');
            }
        }
        return elems_info.toString();
    }

    @Override
    public String print_field_descending_health() {
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (SpaceMarine elem : this.collectionSpaceMarine){
            heights.add(elem.getHealth());
        }
        heights.sort(Comparator.reverseOrder());
        return heights.toString();
    }

    @NotNull
    @Override
    public Iterator<SpaceMarine> iterator() {
        return this.collectionSpaceMarine.iterator();
    }

    public int size(){
        return this.collectionSpaceMarine.size();
    }

    public SpaceMarine get_by_id(int id) {
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getId() == id){
                return elem;
            }
        }
        return null;
    }
}
