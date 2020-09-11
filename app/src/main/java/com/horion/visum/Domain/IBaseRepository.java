package com.horion.visum.Domain;

import androidx.lifecycle.LiveData;

import java.util.List;

// interface pour les repositories

public interface IBaseRepository<T> {

    interface GetItemCallback<T>{

        void onItemloaded(T item);

        void onDataNotAvaible();
    }

    void insert(T item);

    void replace(T item);

    void upsert(T item);

    void delete(String name);

    int count();

    LiveData<List<T>> getAll();

    void get(int id, GetItemCallback<T> getItemCallback);
}
