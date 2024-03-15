package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.error.ElementNotFound;
import io.github.zeculesu.itmo.prog5.error.EmptyCollectionException;
import io.github.zeculesu.itmo.prog5.error.IdException;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Класс, описывающий коллекцию из элементов SpaceMarine
 */
public class SpaceMarineCollection implements CollectionAction {

    /**
     * Сама коллекция
     */
    private static int nextId = 1;
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

    public void add(SpaceMarine o) {
        this.collectionSpaceMarine.add(o);
        nextId++;
    }

    /**
     * Обновление значений полей имеющегося элемента через его id
     *
     * @param id id элемента для изменения
     * @param o  элемент с новыми полями
     * @throws ElementNotFound в коллекции нет элемента с таким id
     */
    @Override
    public void update(int id, SpaceMarine o) throws ElementNotFound {
        SpaceMarine elem = findById(id);
        elem.setName(o.getName());
        elem.setCoordinates(o.getCoordinates());
        elem.setHealth(o.getHealth());
        elem.setCategory(o.getCategory());
        elem.setWeaponType(o.getWeaponType());
        elem.setMeleeWeapon(o.getMeleeWeapon());
        elem.setChapter(o.getChapter());
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
     */
    public void setNewMaxId() {
        for (SpaceMarine elem : this.collectionSpaceMarine) {
            if (elem.getId() >= nextId) nextId = elem.getId() + 1;
        }
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
        return (ArrayList<SpaceMarine>) this.collectionSpaceMarine.stream().filter(x
                -> x.getName().startsWith(name)).collect(Collectors.toList());
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
        return this.collectionSpaceMarine.stream().map(SpaceMarine::getHealth).sorted(Comparator.reverseOrder()).
                map(Object::toString).collect(Collectors.joining("\n"));
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
        SpaceMarine o = this.collectionSpaceMarine.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (o != null) return o;
        throw new ElementNotFound("Элемента с таким id нет в коллекции");
    }

    public static int getNextId() {
        return nextId;
    }
}
