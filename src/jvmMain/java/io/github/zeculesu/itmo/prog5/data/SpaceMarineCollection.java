package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.error.ElementNotFound;
import io.github.zeculesu.itmo.prog5.error.EmptyCollectionException;
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
    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Тип коллекции: " + this.collectionSpaceMarine.getClass().getName());
        info.add("Дата инициализации: " + this.dateInitialization.toString());
        info.add("Количество элементов: " + this.collectionSpaceMarine.toArray().length);
        return info;
    }

    @Override
    public ArrayList<SpaceMarine> show() throws EmptyCollectionException {
        if (this.size() == 0) {
            throw new EmptyCollectionException();
        }
        ArrayList<SpaceMarine> showCollection = new ArrayList<>();
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            showCollection.add(elem);
        }
        return showCollection;
    }

    @Override
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
    public void update(int id, String name, Coordinates coordinates, int health,
                         AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) throws ElementNotFound {
        SpaceMarine elem = findById(id);
        elem.setName(name);
        elem.setCoordinates(coordinates);
        elem.setHealth(health);
        elem.setCategory(category);
        elem.setWeaponType(weaponType);
        elem.setMeleeWeapon(meleeWeapon);
        elem.setChapter(chapter);
    }

    @Override
    public void removeById(int id) throws ElementNotFound {
        this.collectionSpaceMarine.remove(findById(id));
//        for (SpaceMarine elem : this.collectionSpaceMarine) {
//            if (elem.getId() == id) {
//                this.collectionSpaceMarine.remove(elem);
//                return "Элемент успешно удален";
//            }
//        }
//        return "Элемент с таким id не найден";
    }

    @Override
    public void clear() {
        this.collectionSpaceMarine.clear();
    }

    public String load(String filename) {
        try {
            ParseFileXML.parseFile(filename, this);
        } catch (FileNotFoundException | ParserConfigurationException | SAXException e) {
            return e.getMessage();
        }
        return "Элементы из коллекции загружены";
    }

    @Override
    public void addFromFile(int id, String name, Coordinates coordinates, Date creationDate, int health,
                            AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) throws IdException {
        for (SpaceMarine o : this.collectionSpaceMarine) {
            if (o.getId() == id)
                throw new IdException("id не уникальные, элемент с повторяющимся id загружен не будет");
        }
        this.collectionSpaceMarine.add(new SpaceMarine(id, name, coordinates, creationDate, health,
                category, weaponType, meleeWeapon, chapter));
    }

    @Override
    public void save(String filename) throws FileCollectionException {
        ParseFileXML.writeFile(filename, this);
    }

    @Override
    public SpaceMarine removeHead() throws EmptyCollectionException {
        if (collectionSpaceMarine.peek() == null) {
            throw new EmptyCollectionException();
        }
        SpaceMarine head = this.collectionSpaceMarine.peek();
        this.collectionSpaceMarine.remove(head);
        return head;
    }

    @Override
    public void removeLower(SpaceMarine o) {
        this.collectionSpaceMarine.removeIf(n -> (n.compareTo(o) < 0));
    }

    @Override
    public void removeAllByMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.collectionSpaceMarine.removeIf(n -> (n.getMeleeWeapon() == meleeWeapon));
    }

    @Override
    public ArrayList<SpaceMarine> filterStartsWithName(String name) {
        ArrayList<SpaceMarine> finded = new ArrayList<>();
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getName().startsWith(name)) {
                finded.add(elem);
            }
        }
        return finded;
    }

    @Override
    public String printFieldDescendingHealth() throws EmptyCollectionException {
        if (this.size() == 0) {
            throw new EmptyCollectionException();
        }
        ArrayList<Integer> heights = new ArrayList<>();
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            heights.add(elem.getHealth());
        }
        heights.sort(Comparator.reverseOrder());
        StringBuilder heightsLine = new StringBuilder();
        for (int h : heights) {
            heightsLine.append(h).append("\n");
        }
        heightsLine.deleteCharAt(heightsLine.length() -1);
        heightsLine.deleteCharAt(heightsLine.length() -1);
        return heightsLine.toString();
    }

    @NotNull
    @Override
    public Iterator<SpaceMarine> iterator() {
        return this.collectionSpaceMarine.iterator();
    }

    @Override
    public int size() {
        return this.collectionSpaceMarine.size();
    }

    @Override
    public SpaceMarine findById(int id) throws ElementNotFound {
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getId() == id) {
                return elem;
            }
        }
        throw new ElementNotFound("Элемента с таким id нет в коллекции");
    }
}
