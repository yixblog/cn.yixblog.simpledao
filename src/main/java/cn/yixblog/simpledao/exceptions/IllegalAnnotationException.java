package cn.yixblog.simpledao.exceptions;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

/**
 * when the annotation is not on the field or the getter method
 * Created by dyb on 14-1-5.
 */
public class IllegalAnnotationException extends RuntimeException {
    public IllegalAnnotationException(Class clazz, AccessibleObject obj) {
        super(((Member) obj).getName() + " of class:" + clazz.getName() + " is neither a field nor a getter method");
    }
}
