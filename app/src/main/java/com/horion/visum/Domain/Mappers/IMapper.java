package com.horion.visum.Domain.Mappers;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IMapper<O, E>{

    LiveData<O> entityToObject(LiveData<E> entityLiveData);

    LiveData<List<O>> entityListToObjectList(LiveData<List<E>> entitiesLiveData);

    E objectToEntity(O modelObject);

    List<E> objectLisToEntityList(List<O> modelObjects);

}
