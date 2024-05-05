package io.github.zeculesu.itmo.prog5.net;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.MeleeWeapon;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class UDPProxySpaceMarineCollection implements SpaceMarineCollection {

    @Override
    public List<String> info() {
        return null;
    }

    @Override
    public List<SpaceMarine> show() {
        return null;
    }

    @Override
    public void add(SpaceMarine o) {

    }

    @Override
    public void add(int id, SpaceMarine o) {

    }

    @Override
    public void update(int id, SpaceMarine o) {

    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public SpaceMarine removeHead() {
        return null;
    }

    @Override
    public void removeLower(SpaceMarine o) {

    }

    @Override
    public void removeAllByMeleeWeapon(MeleeWeapon meleeWeapon) {

    }

    @Override
    public List<SpaceMarine> filterStartsWithName(String name) {
        return null;
    }

    @Override
    public String printFieldDescendingHealth() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public SpaceMarine findById(int id) {
        return null;
    }

    @Override
    public void setNewMaxId() {

    }

    @NotNull
    @Override
    public Iterator<SpaceMarine> iterator() {
        return null;
    }
}
