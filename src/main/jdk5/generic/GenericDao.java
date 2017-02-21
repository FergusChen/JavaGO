package main.jdk5.generic;

import main.collection.Person;

import java.util.Set;

/**
 * Created by yudong on 17/2/21.
 * dao: data access object -> crud 增删改查
 *
 * Dao都是增删改查, 方法和类的定义 都要一致
 */
public class GenericDao<T> {
    public void add(T p){

    }

    public T findById(int id){
        return null;
    }

    public void delete(T obj){

    }

    public void update(T obj){

    }

    public Set<T> findByConditions(String cond){
        return null;
    }

    public T findByUserName(String name){
        return null;
    }
}
