package annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangchunhui on 2018/7/22.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLDouble {
    int size() default 0; //长度
    int d() default 0;//小数精度
    String name() default "";
    Constraints contraints() default @Constraints;
}
