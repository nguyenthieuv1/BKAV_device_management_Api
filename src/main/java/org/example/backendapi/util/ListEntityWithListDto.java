package org.example.backendapi.util;

import java.util.ArrayList;
import java.util.List;

public class ListEntityWithListDto<T,V> {
    List<T> entityList;
    List<V> dtoList;


    public List<V> ToDto(){
        this.dtoList = new ArrayList<V>();
        for(T entity : entityList){
//            dtoList.add(entity.to)
        }
        return null;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    public List<V> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<V> dtoList) {
        this.dtoList = dtoList;
    }


}
