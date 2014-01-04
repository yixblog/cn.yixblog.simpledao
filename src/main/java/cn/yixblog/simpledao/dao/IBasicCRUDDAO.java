package cn.yixblog.simpledao.dao;


import java.util.List;

/**
 * base crud dao interface
 * Created by dyb on 14-1-4.
 */
public interface IBasicCRUDDAO<T> {
    public List<T> findMany(T example, List<String> queryFields, int... pageArgs);

    public T findOneByPK(T example);
}
