package com.chemondis.calendar.storage;

import java.util.Map;

public interface Storage<T extends Storable> {

    T getObjectByPrimaryIndex(long index);

    T saveObject(T objectToSave);

    T updateObject(T objectToUpdate);

    Map<Long, T> getAllObjects();

}
