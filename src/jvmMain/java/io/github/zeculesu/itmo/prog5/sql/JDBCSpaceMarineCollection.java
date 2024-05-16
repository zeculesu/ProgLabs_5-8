package io.github.zeculesu.itmo.prog5.sql;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.*;
import org.jetbrains.annotations.NotNull;
import ru.landgrafhomyak.utility.JavaResourceLoader;
import sun.misc.Unsafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCSpaceMarineCollection implements SpaceMarineCollection {
    private final Connection connection;

    public JDBCSpaceMarineCollection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<String> info() {
        List<String> output = new ArrayList<>();
        output.add("Тип коллекции: база данных PostgreSQl");
        output.add("Дата инициализации: " + getCreationDate().toString());
        output.add("Количество элементов: " + size());
        return output;
    }

    private final static String SHOW = loadQuery("show.sql");

    @Override
    public List<SpaceMarine> show() {
        List<SpaceMarine> spaceMarines = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(SHOW)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                spaceMarines.add(new SpaceMarine(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Coordinates(
                                resultSet.getLong("coordinatesX"),
                                resultSet.getFloat("coordinatesY")),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("health"),
                        AstartesCategory.getCategoryByName(resultSet.getString("category")),
                        Weapon.getWeaponByName(resultSet.getString("weaponType")),
                        MeleeWeapon.getMeleeWeaponByName(resultSet.getString("meleeWeapon")),
                        new Chapter(
                                resultSet.getString("chapterName"),
                                resultSet.getString("chapterParentLegion"))
                ));
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return spaceMarines;
    }

    private final static String ADD_QUERY = loadQuery("add.sql");

    @Override
    public int add(SpaceMarine o) {
        try (PreparedStatement ps = this.connection.prepareStatement(ADD_QUERY)) {
            ps.setInt(1, o.getId());
            ps.setString(2, o.getName());
            ps.setLong(3, o.getCoordinates().getX());
            ps.setFloat(4, o.getCoordinates().getY());
            ps.setDate(5, (java.sql.Date) o.getCreationDate());
            ps.setInt(6, o.getHealth());
            ps.setString(7, o.getCategory().name());
            ps.setString(8, o.getWeaponType().name());
            ps.setString(9, o.getMeleeWeapon().name());
            ps.setString(10, o.getChapter().getName());
            ps.setString(11, o.getChapter().getParentLegion());
            ps.executeUpdate();
            return o.getId();
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
            return 0;
        }
    }

    private final static String UPDATE_QUERY = loadQuery("update.sql");

    @Override
    public void update(int id, SpaceMarine o) {
        try (PreparedStatement ps = this.connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, o.getName());
            ps.setLong(2, o.getCoordinates().getX());
            ps.setFloat(3, o.getCoordinates().getY());
            //todo возможно надо переделать
            ps.setDate(4, (java.sql.Date) o.getCreationDate());
            ps.setInt(5, o.getHealth());
            ps.setString(6, o.getCategory().name());
            ps.setString(7, o.getWeaponType().name());
            ps.setString(8, o.getMeleeWeapon().name());
            ps.setString(9, o.getChapter().getName());
            ps.setString(10, o.getChapter().getParentLegion());
            ps.executeUpdate();
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
    }

    private final static String REMOVE_BY_ID_QUERY = loadQuery("removeById.sql");

    @Override
    public boolean removeById(int id) {
        try (PreparedStatement ps = this.connection.prepareStatement(REMOVE_BY_ID_QUERY)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            // Проверяем количество измененных строк
            return rowsAffected > 0;
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
            return false;
        }
    }

    private final static String CLEAR_QUERY = loadQuery("clear.sql");

    @Override
    public void clear() {
        try (PreparedStatement ps = this.connection.prepareStatement(CLEAR_QUERY)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
    }

    private final static String REMOVE_HEAD_QUERY = loadQuery("removeHead.sql");

    @Override
    public SpaceMarine removeHead() {
        try (PreparedStatement ps = this.connection.prepareStatement(REMOVE_HEAD_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    long coordinatesX = rs.getLong("coordinatesX");
                    float coordinatesY = rs.getFloat("coordinatesY");
                    Date creationDate = rs.getDate("creationDate");
                    int health = rs.getInt("health");
                    AstartesCategory category = AstartesCategory.getCategoryByName(rs.getString("category"));
                    Weapon weaponType = Weapon.getWeaponByName(rs.getString("weaponType"));
                    MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeaponByName(rs.getString("meleeWeapon"));
                    String chapterName = rs.getString("chapterName");
                    String chapterParentLegion = rs.getString("chapterParentLegion");
                    SpaceMarine o = new SpaceMarine(id, name, new Coordinates(coordinatesX, coordinatesY),
                            creationDate, health, category, weaponType, meleeWeapon,
                            new Chapter(chapterName, chapterParentLegion));
                    return o;
                }
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return null;
    }

    private final static String REMOVE_LOWER_QUERY = loadQuery("removeLower.sql");

    @Override
    public void removeLower(SpaceMarine o) {
        try (PreparedStatement ps = this.connection.prepareStatement(REMOVE_LOWER_QUERY)) {
            ps.setString(1, o.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
    }

    private final static String REMOVE_BY_MELEEWEAPON_QUERY = loadQuery("removeAllByMeleeWeapon.sql");

    @Override
    public void removeAllByMeleeWeapon(MeleeWeapon meleeWeapon) {
        try (PreparedStatement ps = this.connection.prepareStatement(REMOVE_BY_MELEEWEAPON_QUERY)) {
            ps.setString(1, meleeWeapon.name());
            ps.executeUpdate();
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
    }

    private final static String FILTER_NAME_QUERY = loadQuery("filterStartsWithName.sql");

    @Override
    public List<SpaceMarine> filterStartsWithName(String name) {
        List<SpaceMarine> spaceMarines = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(FILTER_NAME_QUERY)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                SpaceMarine spaceMarine = new SpaceMarine(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Coordinates(
                                resultSet.getLong("coordinatesX"),
                                resultSet.getFloat("coordinatesY")),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("health"),
                        AstartesCategory.getCategoryByName(resultSet.getString("category")),
                        Weapon.getWeaponByName(resultSet.getString("weaponType")),
                        MeleeWeapon.getMeleeWeaponByName(resultSet.getString("meleeWeapon")),
                        new Chapter(
                                resultSet.getString("chapterName"),
                                resultSet.getString("chapterParentLegion"))
                );
                spaceMarines.add(spaceMarine);
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return spaceMarines;
    }

    private final static String HEALTH_QUERY = loadQuery("printFieldDescendingHealth.sql");

    @Override
    public String printFieldDescendingHealth() {
        StringJoiner result = new StringJoiner("\n");
        try (PreparedStatement ps = this.connection.prepareStatement(HEALTH_QUERY)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int value = resultSet.getInt("health");
                result.add(Integer.toString(value));
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return result.toString();
    }

    private final static String SIZE_QUERY = loadQuery("size.sql");

    @Override
    public int size() {
        try (PreparedStatement ps = this.connection.prepareStatement(SIZE_QUERY)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return 0;
    }

    private final static String FIND_BY_ID_QUERY = loadQuery("findById.sql");

    @Override
    public SpaceMarine findById(int id) {
        try (PreparedStatement ps = this.connection.prepareStatement(FIND_BY_ID_QUERY)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new SpaceMarine(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Coordinates(
                                resultSet.getLong("coordinatesX"),
                                resultSet.getFloat("coordinatesY")),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("health"),
                        AstartesCategory.getCategoryByName(resultSet.getString("category")),
                        Weapon.getWeaponByName(resultSet.getString("weaponType")),
                        MeleeWeapon.getMeleeWeaponByName(resultSet.getString("meleeWeapon")),
                        new Chapter(
                                resultSet.getString("chapterName"),
                                resultSet.getString("chapterParentLegion"))
                );
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return null;
    }

    public Date getCreationDate() {
        String query = "SELECT pg_class.relname, pg_stat_user_tables.last_analyze, pg_stat_user_tables.last_autoanalyze, pg_stat_user_tables.last_vacuum, pg_stat_user_tables.last_autovacuum "
                + "FROM pg_class "
                + "JOIN pg_stat_user_tables ON pg_class.relname = pg_stat_user_tables.relname "
                + "WHERE pg_class.relname = 'collection';";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getDate("creation_date");
            }

        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return null;
    }

    @NotNull
    @Override
    public Iterator<SpaceMarine> iterator() {
        List<SpaceMarine> spaceMarines = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(SHOW)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                spaceMarines.add(new SpaceMarine(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Coordinates(
                                resultSet.getLong("coordinatesX"),
                                resultSet.getFloat("coordinatesY")),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("health"),
                        AstartesCategory.getCategoryByName(resultSet.getString("category")),
                        Weapon.getWeaponByName(resultSet.getString("weaponType")),
                        MeleeWeapon.getMeleeWeaponByName(resultSet.getString("meleeWeapon")),
                        new Chapter(
                                resultSet.getString("chapterName"),
                                resultSet.getString("chapterParentLegion"))
                ));
            }
        } catch (SQLException e) {
            Unsafe.getUnsafe().throwException(e);
        }
        return spaceMarines.iterator();
    }

    private static String loadQuery(String filename) {
        return JavaResourceLoader.loadTextRelativeExitOnFail(JDBCSpaceMarineCollection.class, filename);
    }
}
