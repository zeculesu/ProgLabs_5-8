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


/**
 * Класс, описывающий коллекцию из элементов SpaceMarine
 */
public class SpaceMarineCollection implements CollectionAction {

    /**
     * Сама коллекция
     */
    private final PriorityQueue<SpaceMarine> collectionSpaceMarine = new PriorityQueue<>();
    /**
     * Дата создания коллекции
     */
    Date dateInitialization;

    /**
     * Изначально создается пустая коллекция и определяется время создания
     */
    public SpaceMarineCollection() {
        this.dateInitialization = new Date();
    }

    /**
     * Создается список строк, содержащих главную информацию
     *
     * @return список со строками информации о коллекции
     */

    @Override
    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Тип коллекции: " + this.collectionSpaceMarine.getClass().getName());
        info.add("Дата инициализации: " + this.dateInitialization.toString());
        info.add("Количество элементов: " + this.collectionSpaceMarine.toArray().length);
        return info;
    }

    /**
     * Формируется список из элементов коллекции
     *
     * @return список SpaceMarin'ов
     * @throws EmptyCollectionException пустая коллекции
     */

    @Override
    public ArrayList<SpaceMarine> show() throws EmptyCollectionException {
        if (this.size() == 0) {
            throw new EmptyCollectionException();
        }
        return new ArrayList<>(this.collectionSpaceMarine);
    }

    /**
     * Добавление нового элемента в коллецию
     *
     * @param name        имя Spacemarine
     * @param coordinates координаты, где находится (состоит из двух параметров x и y)
     * @param health      количество здоровья
     * @param category    категория Spacemarine
     * @param weaponType  тип оружия
     * @param meleeWeapon тип оружия ближнего боя
     * @param chapter     орден (имя ордена и родительским легион)
     */
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

    /**
     * Обновление значений полей имеющегося элемента через его id
     *
     * @param id          id элемента
     * @param name        имя Spacemarine
     * @param coordinates координаты, где находится (состоит из двух параметров x и y)
     * @param health      количество здоровья
     * @param category    категория Spacemarine
     * @param weaponType  тип оружия
     * @param meleeWeapon тип оружия ближнего боя
     * @param chapter     орден (имя ордена и родительским легион)
     * @throws ElementNotFound в коллекции нет элемента с таким id
     */
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

    /**
     * Удаление элемента из коллекции по его id
     *
     * @param id id элемента
     * @throws ElementNotFound элемента с такими id нет в коллекции
     */
    @Override
    public void removeById(int id) throws ElementNotFound {
        this.collectionSpaceMarine.remove(findById(id));
    }

    /**
     * Очищает коллекцию
     */
    @Override
    public void clear() {
        this.collectionSpaceMarine.clear();
    }

    /**
     * Загрузка элементов из коллекции
     *
     * @param filename имя файла
     * @return вердикт о работе
     */
    public String load(String filename) {
        try {
            ParseFileXML.parseFile(filename, this);
        } catch (FileNotFoundException | ParserConfigurationException | SAXException e) {
            return e.getMessage();
        }
        return "Элементы из коллекции загружены";
    }

    /**
     * Добавление элемента из файла
     *
     * @param id          id элемента
     * @param name        имя Spacemarine
     * @param coordinates координаты, где находится (состоит из двух параметров x и y)
     * @param health      количество здоровья
     * @param category    категория Spacemarine
     * @param weaponType  тип оружия
     * @param meleeWeapon тип оружия ближнего боя
     * @param chapter     орден (имя ордена и родительским легион)
     * @throws IdException элемент с таким id уже имеется в коллекции
     */

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

    /**
     * Сохраняет коллекцию в файл
     *
     * @param filename имя файла
     * @throws FileCollectionException проблемы с записью или доступом коллекции
     */
    @Override
    public void save(String filename) throws FileCollectionException {
        ParseFileXML.writeFile(filename, this);
    }

    /**
     * Удаляет первый элемент коллекции
     *
     * @return возвращает удаленный элемент
     * @throws EmptyCollectionException коллекция пустая
     */
    @Override
    public SpaceMarine removeHead() throws EmptyCollectionException {
        if (collectionSpaceMarine.peek() == null) {
            throw new EmptyCollectionException();
        }
        SpaceMarine head = this.collectionSpaceMarine.peek();
        this.collectionSpaceMarine.remove(head);
        return head;
    }

    /**
     * Удаляет элементы коллекции, которые меньше указанного
     *
     * @param o элемент для сравнения
     */
    @Override
    public void removeLower(SpaceMarine o) {
        this.collectionSpaceMarine.removeIf(n -> (n.compareTo(o) < 0));
    }

    /**
     * Удаляет элементы, у которых поле ближнего боя равно указанному
     *
     * @param meleeWeapon параметр ближнего боя
     */
    @Override
    public void removeAllByMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.collectionSpaceMarine.removeIf(n -> (n.getMeleeWeapon() == meleeWeapon));
    }

    /**
     * Выбирает элементы, у которых имя начинается с заданной подстроки
     *
     * @param name подстрока для поиска
     * @return элементы, у которых имя начинается с заданной подстроки
     */

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

    /**
     * Выбирает у всех элементов колллекции поле здоровья
     *
     * @return строка со всеми количествами здоровья в коллекции, идущих по убыванию
     * @throws EmptyCollectionException коллекция пустая
     */

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
        heightsLine.deleteCharAt(heightsLine.length() - 1);
        heightsLine.deleteCharAt(heightsLine.length() - 1);
        return heightsLine.toString();
    }


    /**
     * Делает коллекцию итерируемой
     *
     * @return итератор
     */
    @NotNull
    @Override
    public Iterator<SpaceMarine> iterator() {
        return this.collectionSpaceMarine.iterator();
    }

    /**
     * Количество элементов в коллекции
     *
     * @return размер коллекции
     */

    @Override
    public int size() {
        return this.collectionSpaceMarine.size();
    }

    /**
     * Получить элемент из коллекции по id
     *
     * @param id id элемента
     * @return элемент SpaceMarine
     * @throws ElementNotFound элемента с таким id нет в коллекции
     */
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
