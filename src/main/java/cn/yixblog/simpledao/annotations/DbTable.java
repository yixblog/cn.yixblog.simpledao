package cn.yixblog.simpledao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * database table
 * Created by dyb on 14-1-4.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DbTable {
    public String value();
    public boolean pkAutoGenerate() default true;
    public String[] primary() default {"id"};
}
