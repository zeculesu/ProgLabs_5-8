package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SpaceMarineCollection implements CollectionAction {
    private final PriorityQueue<SpaceMarine> collectionSpaceMarine = new PriorityQueue<>();
    Date dateInitialization;

    public SpaceMarineCollection() {
        this.dateInitialization = new Date();
    }

    @Override
    public String info(CommandIO console) {
        console.println("Тип коллекции: " + this.collectionSpaceMarine.getClass().getName());
        console.println("Дата инициализации: " + this.dateInitialization.toString());
        console.println("Количество элементов: " + this.collectionSpaceMarine.toArray().length);
        return "...";
    }

    @Override
    public String show(CommandIO console) {
        if (this.size() == 0){
            return "Элементов в коллекции нет";
        }
        for (SpaceMarine elem : this.collectionSpaceMarine){
            console.println(elem.toString());
        }
        return "...";
    }

    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        //todo изменить способ выдачи id
        int maxId = 1;
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getId() >= maxId) maxId =elem.getId()+1;
        }
        //int id = !collectionSpaceMarine.isEmpty() ? collectionSpaceMarine.size() + 1 : 1;
        SpaceMarine newElement = new SpaceMarine(maxId, name, coordinates, health, category, weaponType, meleeWeapon, chapter);
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
    public String remove_by_id(int id) {
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getId() == id){
                this.collectionSpaceMarine.remove(elem);
                return "Элемент успешно удален";
            }
        }
        return "Элемент с таким id не найден";
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
    public String remove_head(CommandIO console) {
        if (collectionSpaceMarine.peek() == null){
            return "Коллекция пустая, из неё нечего обсуждать";
        } //todo проверить что не null
        SpaceMarine head = this.collectionSpaceMarine.peek();
        this.collectionSpaceMarine.remove(head);
        console.println(head.toString());
        return "...";
    }

    @Override
    public String remove_lower(SpaceMarine o) {
        int start = this.size();
        this.collectionSpaceMarine.removeIf(n -> (n.compareTo(o) < 0));
        int end = this.size();
        if (start == end){
            return "Элементов с меньше данного в коллекции не найдено";
        }
        return "Удаление произошло успешно";
    }

    @Override
    public String remove_all_by_melee_weapon(MeleeWeapon meleeWeapon) {
        int start = this.size();
        this.collectionSpaceMarine.removeIf(n -> (n.getMeleeWeapon() == meleeWeapon));
        int end = this.size();
        if (start == end){
            return "Элементов с таким оружием ближнего боя в коллекции не найдено";
        }
        return "Удаление произошло успешно";
    }

    @Override
    public String filter_starts_with_name(String name, CommandIO console) {
        boolean finded = false;
        for (SpaceMarine elem : this.collectionSpaceMarine){
            if (elem.getName().startsWith(name)){
                console.println(elem.toString());
                finded = true;
            }
        }
        return finded ? "..." : "не нашлось ни одного элемента";
    }

    @Override
    public String print_field_descending_health() {
        if (this.size() == 0){
            return "в коллекции пока нет ни одного элемента";
        }
        ArrayList<Integer> heights = new ArrayList<>();
        for (SpaceMarine elem : this.collectionSpaceMarine){
            heights.add(elem.getHealth());
        }
        heights.sort(Comparator.reverseOrder());
        return "...";
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
