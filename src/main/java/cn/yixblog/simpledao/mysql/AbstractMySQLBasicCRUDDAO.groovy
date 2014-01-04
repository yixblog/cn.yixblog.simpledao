package cn.yixblog.simpledao.mysql

import cn.yixblog.simpledao.dao.IBasicCRUDDAO
import org.springframework.jdbc.core.JdbcTemplate

import javax.annotation.Resource

/**
 * Created by dyb on 14-1-4.
 */
abstract class AbstractMySQLBasicCRUDDAO<T> implements IBasicCRUDDAO<T>{
    @Resource
    protected JdbcTemplate jdbcTemplate;
    @Override
    T findOneByPk(T example){

    }
}
