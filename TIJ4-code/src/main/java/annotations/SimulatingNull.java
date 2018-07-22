package annotations;

/**
 * Created by zhangchunhui on 2018/7/22.
 * 默认值限制
 */
public @interface SimulatingNull {
    public int id() default -1;
    public String description() default "";
}
