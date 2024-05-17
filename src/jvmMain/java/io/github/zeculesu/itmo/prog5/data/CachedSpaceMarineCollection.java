package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.models.MeleeWeapon;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedSpaceMarineCollection implements SpaceMarineCollection {
    InMemorySpaceMarineCollection cache;
    SpaceMarineCollection origin;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public CachedSpaceMarineCollection(SpaceMarineCollection origin, InMemorySpaceMarineCollection cache) {
        this.origin = origin;
        this.cache = cache;
    }

    public CachedSpaceMarineCollection(SpaceMarineCollection origin) {
        this.origin = origin;
        this.cache = new InMemorySpaceMarineCollection();
    }

    @Override
    public List<String> info() {
        return this.origin.info();
    }

    @Override
    public List<SpaceMarine> show() {
        return this.cache.show();
    }

    @Override
    public int add(SpaceMarine o) {
        int id = this.origin.add(o);
        this.cache.add(id, o);
        return id;
    }

    @Override
    public void update(int id, SpaceMarine o) {
        this.origin.update(id, o);
        this.cache.update(id, o);
    }

    @Override
    public boolean removeById(int id) {
        if (this.origin.removeById(id)) {
            this.cache.removeById(id);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.origin.clear();
        this.cache.clear();
    }

    @Override
    public SpaceMarine removeHead() {
        SpaceMarine o = this.origin.removeHead();
        this.cache.removeById(o.getId());
        return o;
    }

    @Override
    public void removeLower(SpaceMarine o) {
        this.origin.removeLower(o);
        this.cache.removeLower(o);
    }

    @Override
    public void removeAllByMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.origin.removeAllByMeleeWeapon(meleeWeapon);
        this.cache.removeAllByMeleeWeapon(meleeWeapon);
    }

    @Override
    public List<SpaceMarine> filterStartsWithName(String name) {
        return this.cache.filterStartsWithName(name);
    }

    @Override
    public String printFieldDescendingHealth() {
        return this.cache.printFieldDescendingHealth();
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public SpaceMarine findById(int id) {
        return this.cache.findById(id);
    }

    @NotNull
    @Override
    public Iterator<SpaceMarine> iterator() {
        return this.cache.iterator();
    }
}
