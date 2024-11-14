package com.example.planme.data.repository;

import com.example.planme.data.models.Entity;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseRepository<TEntity extends Entity> {

    private final ArrayList<Entity> dbLocal;
    public BaseRepository(ArrayList<Entity> dbLocal){
        this.dbLocal = dbLocal;
    }

    public void add(Entity entity) {
        this.dbLocal.add(entity);
    }

    public void remove(Entity entity) {
        this.dbLocal.remove(entity);
    }
    public abstract ArrayList<TEntity> getAll();
    public abstract TEntity getById(String id);
    public <T extends Entity> ArrayList<T> filterByClass(Class<T> clazz) {
        return this.dbLocal.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
