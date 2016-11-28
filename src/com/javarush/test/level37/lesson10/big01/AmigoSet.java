package com.javarush.test.level37.lesson10.big01;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by MVTitov on 07.11.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable {

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max(16, (int) (collection.size() / .75f)));
        addAll(collection);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(map.size());
        for (E e : map.keySet()) {
            out.writeObject(e);
        }
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        Set<E> set = new HashSet();
        for (int i = 0; i < size; i++) {
            set.add((E) in.readObject());
        }
        Integer capacity = (Integer) in.readObject();
        Float loadFactor = (Float) in.readObject();
        map = new HashMap<>(capacity, loadFactor);
        for (E e : set) {
            map.put(e, PRESENT);
        }
    }

    @Override
    public boolean add(E e) {
        Object res = map.put(e, PRESENT);
        return res != PRESENT;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return PRESENT == map.remove(o);
    }

    @Override
    public Object clone() throws InternalError {
        AmigoSet<E> amigoSet = new AmigoSet<>();
        try {
            amigoSet.addAll(this);
            amigoSet.map.putAll(this.map);
        } catch (Exception e) {
            throw new InternalError();
        }
        return amigoSet;
    }
}
