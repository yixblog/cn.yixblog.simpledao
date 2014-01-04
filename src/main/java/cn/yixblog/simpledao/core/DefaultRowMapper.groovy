package cn.yixblog.simpledao.core

import cn.yixblog.simpledao.annotations.DbColumn
import cn.yixblog.simpledao.annotations.DbTable
import cn.yixblog.simpledao.core.beans.ColumnConfig
import cn.yixblog.simpledao.exceptions.IllegalAnnotationException
import cn.yixblog.simpledao.exceptions.NotAnnotatedTypeException
import org.springframework.jdbc.core.RowMapper

import java.lang.reflect.AccessibleObject
import java.lang.reflect.Field
import java.lang.reflect.Member
import java.lang.reflect.Method
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Created by dyb on 14-1-5.
 */
class DefaultRowMapper<T> implements RowMapper<T> {
    private DbTable tableConfig;
    private Map<String, ColumnConfig> columnConfigs;

    DefaultRowMapper() {
        resolveObjectAnnotations();
    }

    @Override
    T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null
    }

    private void resolveObjectAnnotations() {
        Class clazz = T.class;
        tableConfig = clazz.getAnnotation(DbTable.class);
        if (tableConfig == null) {
            throw new NotAnnotatedTypeException(T.class);
        }
        columnConfigs = [:];
        resolveColumnConfigs(clazz.getDeclaredFields(),columnConfigs);
        resolveColumnConfigs(clazz.getDeclaredMethods(),columnConfigs);
    }

    private void resolveColumnConfigs(AccessibleObject[] objects, Map<String, ColumnConfig> map) {
        for (AccessibleObject item : objects) {
            if (item.isAnnotationPresent(DbColumn.class)) {
                ColumnConfig config = resolveColumnConfig(item);
                map.put(config.getColumnName(),config);
            }
        }
    }

    private ColumnConfig resolveColumnConfig(AccessibleObject object) {
        String fieldName;
        String fieldType;
        if (object instanceof Field) {
            fieldName = object.getName();
            fieldType = object.getType().getName();
        } else if (object instanceof Method) {
            fieldName = object.getName();
            fieldType = object.getReturnType().getName();
            if (fieldName.startsWith('get')) {
                fieldName = fieldName.substring(3);
                fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
            } else {
                throw new IllegalAnnotationException(T.class, object);
            }
        } else {
            throw new IllegalAnnotationException(T.class, object);
        }
        DbColumn columnAnno = object.getAnnotation(DbColumn.class);
        String columnName = columnAnno.value();
        if (columnName == "") {
            columnName = fieldName;
        }
        new ColumnConfig(fieldName: fieldName, fieldType: fieldType, columnName: columnName)
    }
}
