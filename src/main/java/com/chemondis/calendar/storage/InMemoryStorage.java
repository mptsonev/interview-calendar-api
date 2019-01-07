package com.chemondis.calendar.storage;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public abstract class InMemoryStorage<T extends Storable> implements Storage<T> {

    protected TreeMap<Long, T> objectList = new TreeMap<>();

    public T getObjectByPrimaryIndex(long index) {
        return objectList.get(index);
    }

    public T saveObject(T objectToSave) {
        long lastObjectId;
        try {
            lastObjectId = objectList.lastKey();
        } catch (NoSuchElementException e) {
            /**
             * Database is empty
             */
            lastObjectId = 0;
        }
        long newObjectId = ++lastObjectId;
        objectToSave.setId(newObjectId);
        objectList.put(newObjectId, objectToSave);
        return objectToSave;
    }

    public T updateObject(T objectToUpdate) {
        T savedObject = objectList.get(objectToUpdate.getId());
        if (savedObject == null) {
            return null;
        }
        return objectList.put(objectToUpdate.getId(), objectToUpdate);
    }

    public Map<Long, T> getAllObjects() {
        return this.objectList;
    }

}
