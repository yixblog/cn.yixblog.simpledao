package cn.yixblog.simpledao.exceptions;

/**
 * thrown when the given class has no annotations of this tool
 * Created by dyb on 14-1-5.
 */
public class NotAnnotatedTypeException extends RuntimeException {
    public NotAnnotatedTypeException(Class clazz){
        super("ClassType:"+clazz.getName()+" is not annotated by simpleDAO");
    }
}
