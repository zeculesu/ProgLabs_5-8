package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.error.FileCollectionException;
import io.github.zeculesu.itmo.prog5.error.IdException;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.manager.ParseFileXML;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
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
        if (this.size() == 0) {
            return "Элементов в коллекции нет";
        }
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            console.println(elem.toString());
        }
        return "...";
    }

    public void add(String name, Coordinates coordinates, int health,
                    AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        int maxId = 1;
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getId() >= maxId) maxId = elem.getId() + 1;
        }
        SpaceMarine newElement = new SpaceMarine(maxId, name, coordinates, health, category, weaponType, meleeWeapon, chapter);
        this.collectionSpaceMarine.add(newElement);
    }

    @Override
    public String update(int id, String name, Coordinates coordinates, int health,
                         AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getId() == id) {
                elem.setName(name);
                elem.setCoordinates(coordinates);
                elem.setHealth(health);
                elem.setCategory(category);
                elem.setWeaponType(weaponType);
                elem.setMeleeWeapon(meleeWeapon);
                elem.setChapter(chapter);
                return "Элемент успешно обновлен";
            }
        }
        return "Элемент не найден";
    }

    @Override
    public String remove_by_id(int id) {
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getId() == id) {
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

    public String load(String filename) {
        try{
            ParseFileXML.parseFile(filename, this);
        } catch (FileNotFoundException | ParserConfigurationException | SAXException e) {
            return e.getMessage();
        }
        return "Элементы из коллекции загружены";
    }

    @Override
    public void addFromFile(int id, String name, Coordinates coordinates, Date creationDate, int health,
                            AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) throws IdException {
        for (SpaceMarine o : this.collectionSpaceMarine){
            if (o.getId() == id) throw new IdException("id не уникальные, элемент с повторяющимся id загружен не будет");
        }
        this.collectionSpaceMarine.add(new SpaceMarine(id, name, coordinates, creationDate, health,
                category, weaponType, meleeWeapon, chapter));
    }

    @Override
    public String save(String filename) {
        try {
            ParseFileXML.writeFile(filename, this);
        } catch (FileCollectionException e) {
            return e.getMessage();
        }
        return "Сохранение прошло успешно";
    }

    @Override
    public String remove_head(CommandIO console) {
        if (collectionSpaceMarine.peek() == null) {
            return "Коллекция пустая, из неё нечего удалять";
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
        if (start == end) {
            return "Элементов с меньше данного в коллекции не найдено";
        }
        return "Удаление произошло успешно";
    }

    @Override
    public String remove_all_by_melee_weapon(MeleeWeapon meleeWeapon) {
        int start = this.size();
        this.collectionSpaceMarine.removeIf(n -> (n.getMeleeWeapon() == meleeWeapon));
        int end = this.size();
        if (start == end) {
            return "Элементов с таким оружием ближнего боя в коллекции не найдено";
        }
        return "Удаление произошло успешно";
    }

    @Override
    public String filter_starts_with_name(String name, CommandIO console) {
        boolean finded = false;
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getName().startsWith(name)) {
                console.println(elem.toString());
                finded = true;
            }
        }
        return finded ? "..." : "не нашлось ни одного элемента\n...";
    }

    @Override
    public String print_field_descending_health() {
        if (this.size() == 0) {
            return "в коллекции пока нет ни одного элемента";
        }
        ArrayList<Integer> heights = new ArrayList<>();
        for (SpaceMarine elem : this.collectionSpaceMarine) {
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

    public int size() {
        return this.collectionSpaceMarine.size();
    }

    public SpaceMarine getById(int id) {
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getId() == id) {
                return elem;
            }
        }
        return null;
    }
}
